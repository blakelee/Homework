package net.blakelee.homework.activities

import android.app.Activity
import android.app.DialogFragment
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import net.blakelee.homework.R
import net.blakelee.homework.adapters.EditClassDayAdapter
import net.blakelee.homework.fragments.DayPicker
import net.blakelee.homework.fragments.TimePicker
import net.blakelee.homework.interfaces.EditClassInterface
import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.models.Week
import net.blakelee.homework.presenters.EditClassPresenter
import net.blakelee.homework.views.EditClassUI
import org.jetbrains.anko.*
import java.util.*

class EditClassActivity : AppCompatActivity(), EditClassInterface {

    private val PICTURE_RESULT = 100
    private val imgView by lazy { find<ImageView>(R.id.edit_class_image) }
    private val phoneNumber by lazy {find<EditText>(R.id.phone_number)}
    private val recycler by lazy {find<RecyclerView>(R.id.days_recycler)}
    private val nameText by lazy {find<EditText>(R.id.class_name)}
    private var validName : Boolean = true
    private lateinit var classDetails : ClassDetails
    private lateinit var presenter : EditClassPresenter
    private var classId : Int? = null
    override fun onFinishEditDialog(daysSelected: List<Int>, position: Int) {
        classDetails.weeks.week[position].day = daysSelected
        recycler.adapter.notifyItemChanged(position)
    }

    override fun openDaysDialog(daysSelected: List<Int>, position: Int) {
        val dp = DayPicker(daysSelected, position)
        dp.show(fragmentManager, "DAY_PICKER")
    }

    override fun openTimePicker(date: Date, compareTime: (Date) -> Int) {
        val tp : DialogFragment = TimePicker(date, compareTime)
        tp.show(fragmentManager, "TIME_PICKER")
    }

    override fun setClassDetails(classDetails: ClassDetails) {
        this.classDetails = classDetails
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras
        classId = bundle?.getInt("class_id")

        presenter = EditClassPresenter(classId, this)

        EditClassUI(classDetails).setContentView(this)

        val toolbar = find<Toolbar>(R.id.toolbar_edit)
        setSupportActionBar(toolbar)

        val arrow : Drawable = resources.getDrawable(R.drawable.abc_ic_ab_back_material)
        arrow.setColorFilter(resources.getColor(R.color.icons), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(arrow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setTitle(R.string.edit_class)
        classId?.let{ supportActionBar?.setTitle(R.string.new_class) }

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = EditClassDayAdapter(classDetails.weeks.week, this, recycler)
        phoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_class_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
            R.id.action_save -> {
                if (validate(validName)) {
                    presenter.save()
                    if (validName) { //A save can change validName
                        NavUtils.navigateUpFromSameTask(this)
                        return true
                    }
                }
                return false
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun validate(validName : Boolean) : Boolean {
        this.validName = validName

        //TODO: Fix it so it checks to see if there is a duplicate name
        //if (!validName)
         //   nameText.error = "Class name is the same as an existing class name"

        /*else */if (classDetails.name.isNotEmpty()) {
            for(item : Week in classDetails.weeks.week) {
                val week = item.day

                if (week.isEmpty()) {
                    alert("You can't have no class days selected") { okButton{} }.show()
                    return false
                }
            }
            return true
        }

        else
            nameText.error = "You must have a class name"

        return false
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            PICTURE_RESULT -> when (resultCode) {
                Activity.RESULT_OK -> {
                    val selectedImageUri = data?.data
                    if (null != selectedImageUri) {
                        imgView.setImageURI(selectedImageUri)
                        imgView.scaleType = ImageView.ScaleType.CENTER_CROP
                        imgView.tag = "IMAGE"
                    }
                }

                Activity.RESULT_CANCELED -> {
                    if (imgView.tag == "BACKGROUND")
                        imgView.scaleType = ImageView.ScaleType.CENTER
                    else
                        imgView.scaleType = ImageView.ScaleType.CENTER_CROP
                }
            }
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}