package net.blakelee.homework.models

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.edit_class_days.view.*

class EditDaysItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val daysSelected = ArrayList<Int>()

    fun bind(week: Week, position: Int) {
        with(itemView) {
            day_picker.text = week.day.day
            day_start.text = week.day.startTime
            day_end.text = week.day.endTime
        }

        daysSelected.clear()

        if (position > 0)
            itemView.addremove_day.text = "-"
        else
            itemView.addremove_day.text = "+"
    }
}