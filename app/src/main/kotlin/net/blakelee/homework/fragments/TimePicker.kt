
package net.blakelee.homework.fragments

import android.app.Dialog
import android.app.DialogFragment
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import kotlinx.android.synthetic.main.edit_class_days.view.*
import net.blakelee.homework.R
import org.jetbrains.anko.alert
import org.jetbrains.anko.ctx
import org.jetbrains.anko.okButton
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class TimePicker (view: View, val time: String): DialogFragment(), TimePickerDialog.OnTimeSetListener {

    val mView = view
    val dateFormat : DateFormat = SimpleDateFormat("h:mma", Locale.getDefault())

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = dateFormat.parse(time)
        return TimePickerDialog(ctx, this, date.hours, date.minutes, false)
    }

    override fun onTimeSet(timeView: TimePicker?, hour: Int, minute: Int) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)

        val newTime = dateFormat.format(cal.time)
        val id = mView.id
        mView.id = -1

        when(id) {
            R.id.day_start -> {
                if (compare(newTime, mView.day_end.text.toString()) > 0)
                    alert("Start time must be before end time") { okButton{} }.show()
                else
                    mView.day_start.text = newTime
            }
            R.id.day_end -> {
                if (compare(newTime, mView.day_start.text.toString()) < 0)
                    alert("End time must be after start time") { okButton{} }.show()
                else
                    mView.day_end.text = newTime
            }
        }
    }

    fun compare(date1str : String, date2str: String) : Int {
        val date1 : Date = dateFormat.parse(date1str)
        val date2 : Date = dateFormat.parse(date2str)
        return date1.compareTo(date2)
    }
}
