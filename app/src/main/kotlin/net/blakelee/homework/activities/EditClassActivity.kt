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
import android.text.format.DateUtils
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import net.blakelee.homework.R
import net.blakelee.homework.adapters.EditClassDayAdapter
import net.blakelee.homework.fragments.DayPicker
import net.blakelee.homework.fragments.TimePicker
import net.blakelee.homework.interfaces.EditClassInterface
import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.presenters.EditClassPresenter
import net.blakelee.homework.views.EditClassUI
import org.jetbrains.anko.*
import java.util.*

class EditClassActivity(val className : String? = null) : AppCompatActivity(), EditClassInterface {

    private val PICTURE_RESULT = 100
    private val imgView by lazy { find<ImageView>(R.id.edit_class_image) }
    private val phoneNumber by lazy {find<EditText>(R.id.phone_number)}
    private val recycler by lazy {find<RecyclerView>(R.id.days_recycler)}
    private lateinit var classDetails : ClassDetails
    private lateinit var presenter : EditClassPresenter

    override fun onFinishEditDialog(daysSelected: List<String>, position: Int) {
        val text = StringBuilder()

        daysSelected.let {
            for(item in it)
                text.append(DateUtils.getDayOfWeekString(item.toInt() + 1, DateUtils.LENGTH_SHORTEST)) //Add 1 because I did 0-6
        }

        if (text.isEmpty())
            text.append("None")

        classDetails.week[position].setWeek(daysSelected)
        classDetails.week[position].day.day = text.toString()
        recycler.adapter.notifyItemChanged(position)
    }

    override fun openDaysDialog(daysSelected: List<String>, position: Int) {
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

        presenter = EditClassPresenter(className, this)

        EditClassUI(classDetails).setContentView(this)

        val toolbar = find<Toolbar>(R.id.toolbar_edit)
        setSupportActionBar(toolbar)

        val arrow : Drawable = resources.getDrawable(R.drawable.abc_ic_ab_back_material)
        arrow.setColorFilter(resources.getColor(R.color.icons), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(arrow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (className == null)
            supportActionBar?.setTitle(R.string.new_class)
        else
            supportActionBar?.setTitle(R.string.edit_class)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = EditClassDayAdapter(classDetails.week, this, recycler)
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
            //R.id.action_save ->
            else -> return super.onOptionsItemSelected(item)
        }
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
}