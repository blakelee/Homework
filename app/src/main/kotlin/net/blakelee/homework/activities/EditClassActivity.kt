package net.blakelee.homework.activities

import android.app.Activity
import android.app.DialogFragment
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import com.raizlabs.android.dbflow.data.Blob
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import net.blakelee.homework.R
import net.blakelee.homework.adapters.EditClassDayAdapter
import net.blakelee.homework.fragments.DayPicker
import net.blakelee.homework.fragments.TimePicker
import net.blakelee.homework.interfaces.EditClassInterface
import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.models.Week
import net.blakelee.homework.presenters.EditClassPresenter
import net.blakelee.homework.utils.BitmapToBlob
import net.blakelee.homework.utils.BlobToBitmap
import net.blakelee.homework.views.EditClassUI
import org.jetbrains.anko.*
import java.io.File
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
    private var classId : Long? = null

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
        classId = bundle?.getLong("class_id")

        presenter = EditClassPresenter(classId, this)

        EditClassUI(classDetails).setContentView(this)

        val toolbar = find<Toolbar>(R.id.toolbar_edit)
        setSupportActionBar(toolbar)

        val arrow : Drawable = resources.getDrawable(R.drawable.abc_ic_ab_back_material)
        arrow.setColorFilter(resources.getColor(R.color.icons), PorterDuff.Mode.SRC_ATOP)

        supportActionBar?.setHomeAsUpIndicator(arrow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setTitle(R.string.new_class)
        classId?.let{ supportActionBar?.setTitle(R.string.edit_class) }

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = EditClassDayAdapter(classDetails.weeks.week, this, recycler)

        phoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        val file : File = File(ctx.filesDir, classDetails.id.toString())

        if (file.exists()) {
            val blob = Blob(file.readBytes())
            imgView.imageBitmap = BlobToBitmap(blob)
            imgView.scaleType = ImageView.ScaleType.CENTER_CROP
            imgView.tag = "IMAGE"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_class_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                val file = File(ctx.filesDir, "temp")

                if (file.exists())
                    file.delete()

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

    //TODO: This needs to get moved to the presenter
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

        when(resultCode) {
            Activity.RESULT_OK -> {
                when (requestCode) {
                    PICTURE_RESULT -> {
                        val image = data?.data
                        image?.let {
                            CropImage.activity(image)
                                    .setAspectRatio(16, 9)
                                    .setAllowRotation(true)
                                    .start(this)
                        }
                    }

                    CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                        val image = CropImage.getActivityResult(data).uri
                        image?.let {
                            imgView.setImageURI(image)
                            imgView.scaleType = ImageView.ScaleType.CENTER_CROP
                            imgView.tag = "IMAGE"

                            //TODO: This is a dangerous call. If you try to save while this is running then there won't be an image
                            launch(CommonPool) {
                                //Convert selected image to bitmap so that we don't need to use storage permissions
                                val bitmap: Bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(image))
                                val file = File(ctx.filesDir, "temp")
                                val blob = BitmapToBlob(bitmap)
                                file.writeBytes(blob.blob)
                            }
                        }
                    }
                }
            }

            Activity.RESULT_CANCELED -> {
                imgView.scaleType = ImageView.ScaleType.CENTER_CROP
                if (imgView.tag == "BACKGROUND")
                        imgView.scaleType = ImageView.ScaleType.CENTER

            }
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}