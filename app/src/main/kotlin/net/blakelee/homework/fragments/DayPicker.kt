package net.blakelee.homework.fragments

import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.text.format.DateUtils.LENGTH_LONG
import android.text.format.DateUtils.getDayOfWeekString
import com.yarolegovich.lovelydialog.LovelyChoiceDialog
import net.blakelee.homework.R
import net.blakelee.homework.interfaces.EditClassInterface
import org.jetbrains.anko.ctx

class DayPicker(val oldDaysSelected : List<Int>, val position : Int) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val ba = BooleanArray(7)

        oldDaysSelected.map { ba[it] = true }

        return LovelyChoiceDialog(ctx)
                .setTitle("Class Days")
                .setIcon(R.drawable.ic_calendar_7)
                .setTopColorRes(R.color.accent)
                .setItemsMultiChoice(Array(7) { i -> getDayOfWeekString(i + 1, LENGTH_LONG) }, ba) { position, _ ->
                    val listener = activity as EditClassInterface
                    listener.onFinishEditDialog(position, this.position)
                }
                .setConfirmButtonText("OK")
                .create()
        }
}