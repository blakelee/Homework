package net.blakelee.homework.activities

import android.app.Activity
import android.app.DialogFragment
import android.arch.lifecycle.ViewModelProviders
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
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.sync.Mutex
import net.blakelee.homework.R
import net.blakelee.homework.adapters.EditClassDayAdapter
import net.blakelee.homework.fragments.DayPicker
import net.blakelee.homework.fragments.TimePicker
import net.blakelee.homework.interfaces.EditClassInterface
import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.utils.BitmapToByteArray
import net.blakelee.homework.utils.ByteArrayToBitmap
import net.blakelee.homework.utils.ClassValidation
import net.blakelee.homework.utils.counter
import net.blakelee.homework.viewmodels.EditClassViewModel
import net.blakelee.homework.views.EditClassUI
import org.jetbrains.anko.*
import java.io.File
import java.util.*

class EditClassActivity : AppCompatActivity(), EditClassInterface {

    private val PICTURE_RESULT = 100
    private val imgView by lazy { find<ImageView>(R.id.edit_class_image) }
    private val phoneNumber by lazy { find<EditText>(R.id.phone_number) }
    private val recycler by lazy { find<RecyclerView>(R.id.days_recycler) }
    private val nameText by lazy { find<EditText>(R.id.class_name) }
    private lateinit var classDetails: ClassDetails
    private lateinit var viewModel: EditClassViewModel
    private var classId: Long? = null

    override fun onFinishEditDialog(daysSelected: List<Int>, position: Int) {
        classDetails.weeks.week[position].day = daysSelected
        recycler.adapter.notifyItemChanged(position)
    }

    override fun openDaysDialog(daysSelected: List<Int>, position: Int) {
        val dp = DayPicker(daysSelected, position)
        dp.show(fragmentManager, "DAY_PICKER")
    }

    override fun openTimePicker(date: Date, set: (Date) -> Unit) {
        val tp: DialogFragment = TimePicker(date, set)
        tp.show(fragmentManager, "TIME_PICKER")
    }

    override fun setClassDetails(classDetails: ClassDetails) {
        this.classDetails = classDetails
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras
        classId = bundle?.getLong("class_id")

        viewModel = ViewModelProviders.of(this).get(EditClassViewModel::class.java)

        if (classId != null)
            classDetails = viewModel.getClass(classId!!)
        else
            classDetails = ClassDetails()

        EditClassUI(classDetails).setContentView(this)

        val toolbar = find<Toolbar>(R.id.toolbar_edit)
        setSupportActionBar(toolbar)

        val arrow: Drawable = resources.getDrawable(R.drawable.abc_ic_ab_back_material)
        arrow.setColorFilter(resources.getColor(R.color.icons), PorterDuff.Mode.SRC_ATOP)

        supportActionBar?.setHomeAsUpIndicator(arrow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setTitle(R.string.new_class)
        classId?.let { supportActionBar?.setTitle(R.string.edit_class) }

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = EditClassDayAdapter(classDetails.weeks.week, this, recycler)

        phoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        classId?.let {
            val file: File = File(ctx.filesDir, classId.toString() + "-image")

            if (file.exists()) {
                val byteArray = file.readBytes()
                imgView.imageBitmap = ByteArrayToBitmap(byteArray)
                imgView.scaleType = ImageView.ScaleType.CENTER_CROP
                imgView.tag = "IMAGE"
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_class_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                val file = File(ctx.filesDir, "temp")
                file.delete()

                NavUtils.navigateUpFromSameTask(this)
                return true
            }
            R.id.action_save -> {
                val result = viewModel.validate(classDetails)

                when (result) {
                    ClassValidation.TIME -> alert("Start time must be less than end time") { okButton{} }.show()
                    ClassValidation.DAYS -> alert("You must have days selected") { okButton{} }.show()
                    ClassValidation.CONFLICT -> nameText.error = "Class name has the same name as another"
                    ClassValidation.EMPTY -> nameText.error = "You must have a class name"
                    ClassValidation.SUCCESS -> {
                        if (classId != null)
                            viewModel.updateClass(classDetails)
                        else
                            viewModel.insertClass(classDetails)

                        NavUtils.navigateUpFromSameTask(this)
                        return true
                    }
                }

                return false
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode) {
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

                            val mutex = Mutex()
                            launch(CommonPool) {
                                mutex.lock()
                                try {
                                    counter.incrementAndGet()
                                    //Convert selected image to bitmap so that we don't need to use storage permissions
                                    val bitmap: Bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(image))
                                    val file = File(ctx.filesDir, "temp")
                                    val blob = BitmapToByteArray(bitmap)
                                    file.writeBytes(blob)
                                    counter.decrementAndGet()
                                } finally {
                                    mutex.unlock()
                                }
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
}