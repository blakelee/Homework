package net.blakelee.homework.activities

import android.app.Activity
import android.app.DialogFragment
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import kotlinx.android.synthetic.main.edit_class_days.view.*
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

class EditClassActivity : AppCompatActivity(), EditClassInterface  {

    private val PICTURE_RESULT = 100
    private val imgView by lazy { find<ImageView>(R.id.edit_class_image) }
    private val phoneNumber by lazy {find<EditText>(R.id.phone_number)}
    private val recycler by lazy {find<RecyclerView>(R.id.days_recycler)}

    private lateinit var view : View

    override fun onFinishEditDialog(daysSelected: ArrayList<Int>) {
        val text = StringBuilder()

        daysSelected.let {
            for(item in it)
                text.append(DateUtils.getDayOfWeekString(item + 1, DateUtils.LENGTH_SHORTEST)) //Add 1 because I did 0-6
        }

        if (text.isEmpty())
            text.append("None")

        view.day_picker.text = text
    }

    override fun openDaysDialog(view: View, daysSelected: ArrayList<Int>) {
        this.view = view
        val args = Bundle()
        args.putIntegerArrayList("week", daysSelected)

        val dp = DayPicker()
        dp.arguments = args
        dp.show(fragmentManager, "DAY_PICKER")
    }

    override fun openTimePicker(view: View, time: String) {
        val tp : DialogFragment = TimePicker(view, time)
        tp.show(fragmentManager, "TIME_PICKER")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EditClassUI(ClassDetails()).setContentView(this)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = EditClassDayAdapter(ClassDetails().week, this, recycler)

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