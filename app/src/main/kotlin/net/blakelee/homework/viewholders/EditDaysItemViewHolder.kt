package net.blakelee.homework.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import net.blakelee.homework.R
import net.blakelee.homework.models.Week
import org.jetbrains.anko.find

class EditDaysItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val day_picker = view.find<Button>(R.id.day_picker)
    val day_start = view.find<Button>(R.id.day_start)
    val day_end = view.find<Button>(R.id.day_end)
    val add_remove = view.find<Button>(R.id.addremove_day)

    fun bind(week: Week, position: Int) {
        day_picker.text = week.getDayAsString()
        day_start.text = week.getStartTimeAsString()
        day_end.text = week.getEndTimeAsString()

        if (position > 0)
            add_remove.text = "-"
        else
            add_remove.text = "+"
    }
}