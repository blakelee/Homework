package net.blakelee.homework.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.edit_class_days.view.*
import net.blakelee.homework.models.EditDaysItemViewHolder
import net.blakelee.homework.interfaces.EditClassInterface
import net.blakelee.homework.models.Week
import net.blakelee.homework.item_views.EditWeekItemUI
import org.jetbrains.anko.AnkoContext
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class EditClassDayAdapter(var week: MutableList<Week>, val editClassInterface: EditClassInterface, val recycler: RecyclerView) : RecyclerView.Adapter<EditDaysItemViewHolder>() {

    val dateFormat : DateFormat = SimpleDateFormat("h:mma", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditDaysItemViewHolder =
            EditDaysItemViewHolder(EditWeekItemUI().createView(AnkoContext.create(parent.context, parent)))

    override fun onBindViewHolder(holder: EditDaysItemViewHolder, position: Int) {

        holder.bind(week[position], position)

        //Add Remove Views
        if (position > 0)
            holder.itemView.addremove_day.setOnClickListener { removeView(holder.itemView) }
        else
            holder.itemView.addremove_day.setOnClickListener { addView(holder.itemView) }

        //Open Day Picker Dialog
        holder.itemView.day_picker.setOnClickListener { editClassInterface.openDaysDialog(week[position].week, position) }

        //Open Time Picker
        holder.itemView.day_start.setOnClickListener {
            val endTime = week[position].day.endTime

            fun compareTime(newTime: String) : Int {
                val date1 : Date = dateFormat.parse(newTime)
                val date2 : Date = dateFormat.parse(endTime)

                if (date1 <= date2) {
                    week[position].day.startTime = newTime
                    notifyItemChanged(position)
                }

                return date1.compareTo(date2)
            }
            editClassInterface.openTimePicker(week[position].day.startTime, ::compareTime )
        }

        holder.itemView.day_end.setOnClickListener {
            val startTime = week[position].day.startTime

            fun compareTime(newTime: String) : Int {
                val date1 : Date = dateFormat.parse(startTime)
                val date2 : Date = dateFormat.parse(newTime)

                if (date1 <= date2) {
                    week[position].day.endTime = newTime
                    notifyItemChanged(position)
                }
                    //holder.itemView.day_end.text = newTime

                return date1.compareTo(date2)
            }
            editClassInterface.openTimePicker(week[position].day.endTime, ::compareTime)
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