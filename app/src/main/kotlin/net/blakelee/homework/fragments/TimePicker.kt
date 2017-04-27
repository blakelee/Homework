
package net.blakelee.homework.fragments

import android.app.Dialog
import android.app.DialogFragment
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import org.jetbrains.anko.alert
import org.jetbrains.anko.ctx
import org.jetbrains.anko.okButton
import java.util.*

class TimePicker (var date: Date, val compareTime: (Date) -> Int): DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return TimePickerDialog(ctx, this, date.hours, date.minutes, false)
    }

    override fun onTimeSet(timeView: TimePicker?, hour: Int, minute: Int) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)
        cal.set(Calendar.YEAR, 1970)
        cal.set(Calendar.DAY_OF_YEAR, 1)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MONTH, 0)
        cal.set(Calendar.MILLISECOND, 0)

        if (compareTime(cal.time) > 0)
            alert("Start time must be before end time") { okButton{} }.show()
    }
}
