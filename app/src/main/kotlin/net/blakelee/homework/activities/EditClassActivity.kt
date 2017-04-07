package net.blakelee.homework.activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import net.blakelee.homework.R
import net.blakelee.homework.fragments.DayPicker
import net.blakelee.homework.views.EditClassUI
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class EditClassActivity : AppCompatActivity(), DayPicker.DayDialogListener  {

    private val PICTURE_RESULT = 100
    private val DAY_PICKER = 101
    private val imgView by lazy { find<ImageView>(R.id.edit_class_image) }
    private val daybutton by lazy  {find<Button>(R.id.day_picker )}
    private val text = StringBuilder()
    private var daysSelected = ArrayList<Int>()

    override fun onFinishEditDialog(daysSelected: ArrayList<Int>) {
        text.setLength(0)
        val days = listOf("S", "M", "T", "W", "R", "F", "Sa")
        daysSelected.let {
            for(item in it)
                text.append(days[item])
        }

        if (text.isEmpty())
            text.append("Days")

        daybutton.text = text
        this.daysSelected = daysSelected
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EditClassUI().setContentView(this)

        daybutton.setOnClickListener {
            val args = Bundle()
            args.putIntegerArrayList("days", daysSelected)

            val dp = DayPicker()
            dp.arguments = args
            dp.setTargetFragment(dp, DAY_PICKER)
            dp.show(fragmentManager, "DAY_PICKER")
        }
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
                    }
                }

                Activity.RESULT_CANCELED -> {
                    //TODO: For some reason it's not detecting if it's the placeholder right. However, it's not throwing errors.
                    if (imgView.drawable == getDrawable(R.drawable.image_placeholder))
                        imgView.scaleType = ImageView.ScaleType.CENTER
                    else
                        imgView.scaleType = ImageView.ScaleType.CENTER_CROP
                }
            }
            DAY_PICKER -> when (resultCode) {
                Activity.RESULT_OK -> {
                    val daysSelected = data?.getIntArrayExtra("Days Picked")
                    val text = ""
                    val days = listOf("s", "m", "t", "w", "tu", "f", "sa")
                    daysSelected?.let {
                        for(item in it)
                            text.plus(days[item])

                        daybutton.text = text
                    }
                }
            }
        }
    }
}