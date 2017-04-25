package net.blakelee.homework.activities

import android.app.Activity
import android.app.DialogFragment
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.format.DateUtils
import android.widget.EditText
import android.widget.ImageView
import net.blakelee.homework.R
import net.blakelee.homework.adapters.EditClassDayAdapter
import net.blakelee.homework.fragments.DayPicker
import net.blakelee.homework.fragments.TimePicker
import net.blakelee.homework.interfaces.EditClassInterface
import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.views.EditClassUI
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import kotlin.collections.ArrayList

class EditClassActivity(var classDetails : ClassDetails = ClassDetails()) : AppCompatActivity(), EditClassInterface  {

    private val PICTURE_RESULT = 100
    private val imgView by lazy { find<ImageView>(R.id.edit_class_image) }
    private val phoneNumber by lazy {find<EditText>(R.id.phone_number)}
    private val recycler by lazy {find<RecyclerView>(R.id.days_recycler)}

    override fun onFinishEditDialog(daysSelected: ArrayList<Int>, position: Int) {
        val text = StringBuilder()

        daysSelected.let {
            for(item in it)
                text.append(DateUtils.getDayOfWeekString(item + 1, DateUtils.LENGTH_SHORTEST)) //Add 1 because I did 0-6
        }

        if (text.isEmpty())
            text.append("None")

        classDetails.week[position].week = daysSelected
        classDetails.week[position].day.day = text.toString()
        recycler.adapter.notifyItemChanged(position)
    }

    override fun openDaysDialog(daysSelected: ArrayList<Int>, position: Int) {
        val dp = DayPicker(daysSelected, position)
        dp.show(fragmentManager, "DAY_PICKER")
    }

    override fun openTimePicker(time: String, compareTime: (String) -> Int) {
        val tp : DialogFragment = TimePicker(time, compareTime)
        tp.show(fragmentManager, "TIME_PICKER")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EditClassUI(classDetails).setContentView(this)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = EditClassDayAdapter(classDetails.week, this, recycler)
        phoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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