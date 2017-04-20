package net.blakelee.homework.activities

import android.app.Activity
import android.app.ActivityManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateUtils.LENGTH_SHORTEST
import android.text.format.DateUtils.getDayOfWeekString
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import net.blakelee.homework.R
import net.blakelee.homework.adapters.EditClassDayAdapter
import net.blakelee.homework.fragments.DayPicker
import net.blakelee.homework.interfaces.EditClassInterface
import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.models.Days
import net.blakelee.homework.views.EditClassUI
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import kotlin.collections.ArrayList

class EditClassActivity : AppCompatActivity(), EditClassInterface  {

    private val PICTURE_RESULT = 100
    private val imgView by lazy { find<ImageView>(R.id.edit_class_image) }
    private val dayButton by lazy  {find<Button>(R.id.day_picker )}
    private val phoneNumber by lazy {find<EditText>(R.id.phone_number)}
    private val recycler by lazy {find<RecyclerView>(R.id.days_recycler)}
    private val text = StringBuilder()
    private var daysSelected = ArrayList<Int>()
    var classDetails = ClassDetails("", mutableListOf(Days("None", "8:00am", "3:00pm")), "", "", "", "", 0, "", "", Days("Date", "8:00am", "3:00pm"))

    override fun onFinishEditDialog(daysSelected: ArrayList<Int>) {
        text.setLength(0)
        daysSelected.let {
            for(item in it)
                text.append(getDayOfWeekString(item + 1, LENGTH_SHORTEST)) //Add 1 because I did 0-6
        }

        if (text.isEmpty())
            text.append("None")

        dayButton.text = text
        this.daysSelected = daysSelected
    }

    override fun openDaysDialog(daysSelected: ArrayList<Int>) {
        val args = Bundle()
        args.putIntegerArrayList("days", daysSelected)

        val dp = DayPicker()
        dp.arguments = args
        dp.show(fragmentManager, "DAY_PICKER")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        /**
         * This is temporary until I get a db added
         */
        //classDetails = ClassDetails("", mutableListOf(Days("None", "8:00am", "3:00pm")), "", "", "", "", 0, "", "", Days("Date", "8:00am", "3:00pm"))

        EditClassUI(classDetails, this).setContentView(this)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = EditClassDayAdapter(classDetails.days, this, recycler)


        //Formats number to appear like (###) ###-####
        phoneNumber.addTextChangedListener(object : TextWatcher {
            var length_before = 0
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                length_before = phoneNumber.text.toString().length
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable) {
                if (length_before < s.length) {
                    if(s.length == 1)
                            s.insert(0,"(")

                    if(s.length > 4)
                        if (Character.isDigit(s[4]))
                            s.insert(4, ")")

                    if (s.length > 5)
                        if (Character.isDigit(s[5]))
                            s.insert(5, " ")

                    if (s.length > 9)
                        if (Character.isDigit(s[9]))
                            s.insert(9, "-")
                }

                if(s.length > 14)
                    s.delete(14,15)
            }
        })

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