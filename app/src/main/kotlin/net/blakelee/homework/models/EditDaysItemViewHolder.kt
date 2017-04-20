package net.blakelee.homework.models

import android.app.Activity
import android.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import org.jetbrains.anko.find
import net.blakelee.homework.R
import net.blakelee.homework.adapters.EditClassDayAdapter
import net.blakelee.homework.interfaces.EditClassInterface

class EditDaysItemViewHolder(item: View) : RecyclerView.ViewHolder(item), View.OnClickListener {
    val day: Button = item.find(R.id.day_picker)
    val dayStart: Button = item.find(R.id.day_start)
    val dayEnd: Button = item.find(R.id.day_end)
    var daysSelected = ArrayList<Int>()
    val addremove: Button = item.find(R.id.addremove_day)
    val item = item

    fun bind(days : Days, act: Activity, position: Int, adapter: EditClassDayAdapter) {
        day.text = days.day
        dayStart.text = days.startTime
        dayEnd.text = days.endTime

        val listener = act as EditClassInterface
        day.setOnClickListener {
            listener.openDaysDialog(daysSelected)
        }

        if (position > 0)
            addremove.text = "-"

        addremove.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                if (position > 0)
                    adapter.remove(item)
                else
                    adapter.add(item)
            }
        }
    }

    override fun onClick(v: View?) {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION) {

        }
    }
}