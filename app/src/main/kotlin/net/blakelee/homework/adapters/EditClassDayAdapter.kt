package net.blakelee.homework.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.edit_class_days.view.*
import net.blakelee.homework.R
import net.blakelee.homework.models.EditDaysItemViewHolder
import net.blakelee.homework.interfaces.EditClassInterface
import net.blakelee.homework.models.Week
import net.blakelee.homework.views.WeekItemUI
import org.jetbrains.anko.AnkoContext

class EditClassDayAdapter(val week: MutableList<Week>, val editClassInterface: EditClassInterface, val recycler: RecyclerView) : RecyclerView.Adapter<EditDaysItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditDaysItemViewHolder =
            EditDaysItemViewHolder(WeekItemUI().createView(AnkoContext.create(parent.context, parent)))

    override fun onBindViewHolder(holder: EditDaysItemViewHolder?, position: Int) {
        val week = week[position]
        holder!!.bind(week, position)
        holder.itemView.day_start

        //Add Remove Views
        if (position > 0)
            holder.itemView.addremove_day.setOnClickListener { removeView(holder.itemView) }
        else
            holder.itemView.addremove_day.setOnClickListener { addView(holder.itemView) }

        //Open Day Picker Dialog
        holder.itemView.day_picker.setOnClickListener { editClassInterface.openDaysDialog(holder.itemView, holder.daysSelected) }

        //Open Time Picker
        holder.itemView.day_start.setOnClickListener {
            holder.itemView.id = R.id.day_start
            editClassInterface.openTimePicker(holder.itemView, week.day.startTime)
        }

        holder.itemView.day_end.setOnClickListener {
            holder.itemView.id = R.id.day_end
            editClassInterface.openTimePicker(holder.itemView, week.day.endTime)
        }
    }

    override fun getItemCount(): Int = week.size

    fun removeView(view: View) {
        val position = recycler.getChildAdapterPosition(view)
        if (position != RecyclerView.NO_POSITION) {
            week.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun addView(view: View) {
        if (itemCount < 5) {
            val position = recycler.getChildAdapterPosition(view)
            if (position != RecyclerView.NO_POSITION) {
                week.add(itemCount, Week())
                notifyItemInserted(itemCount - 1)
            }
        }
    }
}