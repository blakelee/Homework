
package net.blakelee.homework.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import org.jetbrains.anko.ctx

class TimePicker : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return TimePickerDialog(ctx, this, 11, 0, false)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

    }

}