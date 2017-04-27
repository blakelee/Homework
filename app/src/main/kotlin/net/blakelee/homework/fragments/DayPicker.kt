package net.blakelee.homework.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.text.format.DateUtils.LENGTH_LONG
import android.text.format.DateUtils.getDayOfWeekString
import net.blakelee.homework.interfaces.EditClassInterface
import java.util.*

class DayPicker(val oldDaysSelected : List<String>, val position : Int) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val ba = BooleanArray(7)
        val daysSelected : ArrayList<String> = ArrayList(oldDaysSelected)

        oldDaysSelected.map {
            ba[it.toInt()] = true
        }


        with(builder) {
            setTitle("Class Days")
            setMultiChoiceItems(Array(7) { i -> getDayOfWeekString(i + 1, LENGTH_LONG) }, ba) { _, which, isChecked ->
                if (isChecked)
                    daysSelected.add(which.toString())
                else
                    daysSelected.remove(which.toString())
            }
            setPositiveButton("OK") { _, _ ->
                daysSelected.sort()
                val listener = activity as EditClassInterface
                listener.onFinishEditDialog(daysSelected.toList(), position)
            }
        }
        return builder.create()
    }
}