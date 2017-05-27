
package net.blakelee.homework.fragments

import android.app.Dialog
import android.app.DialogFragment
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import net.blakelee.homework.utils.getHour
import org.jetbrains.anko.ctx
import java.util.*

class TimePicker (var date: Date, val position: Int, val set: (Date, Int) -> Unit): DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return TimePickerDialog(ctx, this, date.hours, date.minutes, false)
    }

    override fun onTimeSet(timeView: TimePicker?, hour: Int, minute: Int) {
        set(getHour(hour, minute), position)
    }
}
