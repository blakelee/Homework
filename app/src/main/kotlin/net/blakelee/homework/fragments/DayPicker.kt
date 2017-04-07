package net.blakelee.homework.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle

class DayPicker : DialogFragment() {
    private val days = listOf(
            "Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday")

    private val DAY_PICKER = 101
    private var daysSelected = ArrayList<Int>()

    interface DayDialogListener {
        fun onFinishEditDialog(daysSelected : ArrayList<Int>)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val ba = BooleanArray(days.size)
        daysSelected = arguments.getIntegerArrayList("days")

        for(i in daysSelected)
            ba[i] = true

        with(builder) {
            setTitle("Pick Day")
            setMultiChoiceItems(Array(days.size) { i -> days[i] }, ba) { _, which, isChecked ->
                if (isChecked)
                    daysSelected.add(which)
                else if (daysSelected.contains(which))
                    daysSelected.remove(which)
            }
            setPositiveButton("OK") { _, _ ->
                daysSelected.sort()
                val listener = activity as DayDialogListener
                listener.onFinishEditDialog(daysSelected)
            }
        }
        return builder.create()
    }
}