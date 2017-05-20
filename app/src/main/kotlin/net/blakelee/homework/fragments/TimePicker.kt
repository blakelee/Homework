
package net.blakelee.homework.fragments

import android.app.Dialog
import android.app.DialogFragment
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import org.jetbrains.anko.ctx
import java.util.*

class TimePicker (var date: Date, val compareTime: (Date) -> Unit): DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return TimePickerDialog(ctx, this, date.hours, date.minutes, false)
    }

    override fun onTimeSet(timeView: TimePicker?, hour: Int, minute: Int) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY,   hour)
        cal.set(Calendar.MINUTE,        minute)
        cal.set(Calendar.YEAR,          cal.getActualMinimum(Calendar.YEAR))
        cal.set(Calendar.DAY_OF_YEAR,   cal.getActualMinimum(Calendar.DAY_OF_YEAR))
        cal.set(Calendar.SECOND,        cal.getActualMinimum(Calendar.SECOND))
        cal.set(Calendar.MONTH,         cal.getActualMinimum(Calendar.MONTH))
        cal.set(Calendar.MILLISECOND,   cal.getActualMinimum(Calendar.MILLISECOND))

        compareTime(cal.time)
    }
}
