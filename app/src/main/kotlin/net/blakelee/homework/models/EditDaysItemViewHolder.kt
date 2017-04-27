package net.blakelee.homework.models

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import net.blakelee.homework.R
import org.jetbrains.anko.find

class EditDaysItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val day_picker = view.find<Button>(R.id.day_picker)
    val day_start = view.find<Button>(R.id.day_start)
    val day_end = view.find<Button>(R.id.day_end)
    val add_remove = view.find<Button>(R.id.addremove_day)

    fun bind(week: Week, position: Int) {
        day_picker.text = week.day.day
        day_start.text = week.day.getStartTime()
        day_end.text = week.day.getEndTime()

        if (position > 0)
            add_remove.text = "-"
        else
            add_remove.text = "+"
    }
}