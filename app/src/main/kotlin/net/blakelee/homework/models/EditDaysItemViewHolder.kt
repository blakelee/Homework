package net.blakelee.homework.models

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.edit_class_days.view.*
import net.blakelee.homework.interfaces.EditClassAdapterInterface
import net.blakelee.homework.interfaces.EditClassInterface

class EditDaysItemViewHolder(itemView: View, val editClassInterface: EditClassInterface, val editClassAdapterInterface: EditClassAdapterInterface) : RecyclerView.ViewHolder(itemView) {

    val daysSelected = ArrayList<Int>()

    fun bind(week: Week, position: Int) {
        with(itemView) {
            day_picker.text = week.day.day
            day_start.text = week.day.startTime
            day_end.text = week.day.endTime
        }

        daysSelected.clear()

        //Add Remove Views
        if (position > 0) {
            itemView.addremove_day.text = "-"
            itemView.addremove_day.setOnClickListener {
                editClassAdapterInterface.removeView(itemView)
            }
        }
        else {
            itemView.addremove_day.text = "+"
            itemView.addremove_day.setOnClickListener {
                editClassAdapterInterface.addView(itemView)
            }
        }

        //Open Day Picker Dialog
        itemView.day_picker.setOnClickListener {
            editClassInterface.openDaysDialog(itemView, daysSelected)
        }
    }
}