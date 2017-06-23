package net.blakelee.homework.pickers

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.widget.DatePicker
import java.util.*

class DatePicker(val date: Date, val cb: (Date) -> Unit) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c : Calendar = Calendar.getInstance()
        c.set(date.year, date.month, date.date)
        return DatePickerDialog(activity, activity as DatePickerDialog.OnDateSetListener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        date.year = year
        date.month = month
        date.date = dayOfMonth
        cb(date)
    }
}