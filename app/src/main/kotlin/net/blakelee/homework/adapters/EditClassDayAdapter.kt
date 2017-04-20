package net.blakelee.homework.adapters

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import net.blakelee.homework.R
import net.blakelee.homework.interfaces.EditClassInterface
import net.blakelee.homework.models.Days
import net.blakelee.homework.models.EditDaysItemViewHolder
import net.blakelee.homework.utils.inflate
import kotlin.properties.Delegates

class EditClassDayAdapter(val dayList : MutableList<Days>, activity: Activity, recyclerView: RecyclerView) : RecyclerView.Adapter<EditDaysItemViewHolder>() {

    val act = activity
    val recycler = recyclerView
    var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditDaysItemViewHolder {
        return EditDaysItemViewHolder(parent.inflate(R.layout.edit_class_days))
    }

    override fun onBindViewHolder(holder: EditDaysItemViewHolder?, position: Int) {
        val day = dayList[position]
        holder!!.bind(day, act, position, this)
    }

    override fun getItemCount(): Int {
        return dayList.size
    }

    fun remove(view: View) {
        val position = recycler.getChildAdapterPosition(view)
        if (position != RecyclerView.NO_POSITION) {
            dayList.removeAt(position)
            notifyItemRemoved(position)
            //notifyItemRangeChanged(position, itemCount)
        }
    }

    fun add(view: View) {
        if (itemCount < 5) {

            val position = recycler.getChildAdapterPosition(view)
            if (position != RecyclerView.NO_POSITION) {
                val day = Days("None", "$count", "3:00pm")
                dayList.add(itemCount, day)
                count += 1
                notifyItemInserted(itemCount - 1)
            }
        }
    }
}