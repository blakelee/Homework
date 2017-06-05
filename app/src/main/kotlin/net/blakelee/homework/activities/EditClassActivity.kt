package net.blakelee.homework.activities

import android.app.Activity
import android.app.DialogFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.widget.Toolbar
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.*
import android.widget.*
import com.jrummyapps.android.colorpicker.ColorPickerDialog
import com.jrummyapps.android.colorpicker.ColorPickerDialogListener
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.sync.Mutex
import net.blakelee.homework.R
import net.blakelee.homework.adapters.WeeksAdapter
import net.blakelee.homework.base.BaseLifecycleActivity
import net.blakelee.homework.pickers.DayPicker
import net.blakelee.homework.pickers.IconPicker
import net.blakelee.homework.pickers.TimePicker
import net.blakelee.homework.interfaces.EditClassInterface
import net.blakelee.homework.models.Week
import net.blakelee.homework.utils.BitmapToByteArray
import net.blakelee.homework.utils.ByteArrayToBitmap
import net.blakelee.homework.utils.ClassValidation
import net.blakelee.homework.utils.counter
import net.blakelee.homework.viewmodels.EditClassViewModel
import net.blakelee.homework.views.EditClassUI
import org.jetbrains.anko.*
import java.io.File
import java.util.*

class EditClassActivity : BaseLifecycleActivity<EditClassViewModel>(), EditClassInterface, ColorPickerDialogListener, AdapterView.OnItemSelectedListener {

    override val viewModelClass = EditClassViewModel::class.java
    private val PICTURE_RESULT = 100
    private val imgView by lazy { find<ImageView>(R.id.edit_class_image) }
    private val phoneNumber by lazy { find<EditText>(R.id.phone_number) }
    private val weeksAdapter = WeeksAdapter(this)
    private val nameText by lazy { find<EditText>(R.id.class_name) }
    private val iconPicker by lazy { find<ImageButton>(R.id.icon_picker_button) }
    private val iconColor by lazy { find<ImageButton>(R.id.icon_color_button) }
    private val spinner by lazy { find<Spinner>(R.id.spinner) }
    private var classId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras

        classId = bundle?.getLong("class_id")
        viewModel = ViewModelProviders.of(this).get(viewModelClass)
        viewModel.loadClassById(classId)

        EditClassUI(viewModel.classDetails.value!!, weeksAdapter).setContentView(this)

        subscribeWeeks()
        setupToolbar()
        setImageIfExists()

        phoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        iconPicker.setOnClickListener {
            val ip = IconPicker(this)
            ip.show(fragmentManager, "ICON_PICKER")
        }
        iconColor.setOnClickListener {
            ColorPickerDialog.newBuilder()
                    .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                    .setShowAlphaSlider(false)
                    .setAllowCustom(false)
                    .setColor(viewModel.classDetails.value!!.icon_color)
                    .show(this)
        }
        spinner.onItemSelectedListener = this
    }

    override fun onColorSelected(dialogId: Int, color: Int) {
        iconColor.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        viewModel.setIconColor(color)
    }

    override fun onDialogDismissed(p0: Int) {} //Do nothing
    private fun setImageIfExists() {
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
    private fun setupToolbar() {
        val toolbar = find<Toolbar>(R.id.toolbar_edit)
        setSupportActionBar(toolbar)

        val arrow: Drawable = resources.getDrawable(R.drawable.abc_ic_ab_back_material)
        arrow.setColorFilter(resources.getColor(R.color.icons), PorterDuff.Mode.SRC_ATOP)

        supportActionBar?.setHomeAsUpIndicator(arrow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setTitle(R.string.new_class)
        classId?.let { supportActionBar?.setTitle(R.string.edit_class) }

    }
    private fun subscribeWeeks() {
        viewModel.weeks.observe(this, Observer<MutableList<Week>> {
            it?.let { weeksAdapter.dataSource = it }
        })
    }
    override fun addWeek() {
        viewModel.addWeek()
        weeksAdapter.notifyItemInserted(viewModel.weeks.value!!.size - 1)
    }
    override fun removeWeek(position: Int) = viewModel.removeWeek(position)
    override fun setStartTime(date: Date, position: Int) {
        val tp: DialogFragment = TimePicker(date, position, viewModel::setStartTime)
        tp.show(fragmentManager, "TIME_PICKER")
    }
    override fun setEndTime(date: Date, position: Int) {
        val tp: DialogFragment = TimePicker(date, position, viewModel::setEndTime)
        tp.show(fragmentManager, "TIME_PICKER")
    }

    override fun setIcon(id: Int) {
        iconPicker.setImageDrawable(resources.getDrawable(id))
        viewModel.setIcon(id)
    }
    override fun onFinishEditDialog(daysSelected: List<Int>, position: Int) {
        viewModel.setDay(daysSelected, position)
    }
    override fun openDaysDialog(daysSelected: List<Int>, position: Int) {
        val dp = DayPicker(daysSelected, position)
        dp.show(fragmentManager, "DAY_PICKER")
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
                val result = viewModel.validate()

                when (result) {
                    ClassValidation.TIME -> alert("Start time must be less than end time") { okButton{} }.show()
                    ClassValidation.DAYS -> alert("You must have days selected") { okButton{} }.show()
                    ClassValidation.CONFLICT -> nameText.error = "Class name has the same name as another"
                    ClassValidation.EMPTY -> nameText.error = "You must have a class name"
                    ClassValidation.SUCCESS -> {
                        if (classId != null)
                            viewModel.updateClass()
                        else
                            viewModel.insertClass()

                        NavUtils.navigateUpFromSameTask(this)
                        return true
                    }
                }

                return false
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.setRingmode(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

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