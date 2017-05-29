package net.blakelee.homework.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import net.blakelee.homework.R
import net.blakelee.homework.base.BaseAdapter
import net.blakelee.homework.base.BaseViewHolder
import net.blakelee.homework.interfaces.EditClassInterface
import net.blakelee.homework.models.Week
import net.blakelee.homework.views.items.EditWeekItemUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class WeeksAdapter(val editClassInterface: EditClassInterface) : BaseAdapter<Week, WeeksAdapter.WeekViewHolder>() {

    override fun getItemViewId(): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): WeekViewHolder =
            WeekViewHolder(EditWeekItemUI().createView(AnkoContext.create(parent!!.context, parent)))

    override fun instantiateViewHolder(view: View?): WeekViewHolder =
            WeekViewHolder(view)

    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) {

        holder.onBind(getItem(position))

        holder.day_picker.setOnClickListener { editClassInterface.openDaysDialog(getItem(position).day, position) }
        holder.day_start.setOnClickListener { editClassInterface.setStartTime(getItem(position).startTime, position) }
        holder.day_end.setOnClickListener { editClassInterface.setEndTime(getItem(position).endTime, position) }
        holder.add_remove.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                if (holder.adapterPosition > 0) {
                    editClassInterface.removeWeek(holder.adapterPosition)
                } else if (itemCount < 5) {
                    editClassInterface.addWeek()
                }
            }
        }
    }

    class WeekViewHolder(itemView: View?) : BaseViewHolder<Week>(itemView) {
        val day_picker = itemView!!.find<Button>(R.id.day_picker)
        val day_start = itemView!!.find<Button>(R.id.day_start)
        val day_end = itemView!!.find<Button>(R.id.day_end)
        val add_remove = itemView!!.find<Button>(R.id.addremove_day)

        override fun onBind(item: Week) {
            val pos = layoutPosition
            day_picker.text = item.getDayAsString()
            day_start.text = item.getStartTimeAsString()
            day_end.text = item.getEndTimeAsString()

            if (pos > 0)
                add_remove.text = "-"
            else
                add_remove.text = "+"
        }
    }
}