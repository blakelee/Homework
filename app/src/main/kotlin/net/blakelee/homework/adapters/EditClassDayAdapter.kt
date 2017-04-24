package net.blakelee.homework.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import net.blakelee.homework.models.EditDaysItemViewHolder
import net.blakelee.homework.interfaces.EditClassAdapterInterface
import net.blakelee.homework.interfaces.EditClassInterface
import net.blakelee.homework.models.Week
import net.blakelee.homework.views.WeekItemUI
import org.jetbrains.anko.AnkoContext

class EditClassDayAdapter(val week: MutableList<Week>, val editClassInterface: EditClassInterface, val recycler: RecyclerView) : RecyclerView.Adapter<EditDaysItemViewHolder>(), EditClassAdapterInterface {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditDaysItemViewHolder =
            EditDaysItemViewHolder(WeekItemUI().createView(AnkoContext.create(parent.context, parent)), editClassInterface, this)

    override fun onBindViewHolder(holder: EditDaysItemViewHolder?, position: Int) {
        val day = week[position]
        holder!!.bind(day, position)
    }

    override fun getItemCount(): Int = week.size

    override fun removeView(view: View) {
        val position = recycler.getChildAdapterPosition(view)
        if (position != RecyclerView.NO_POSITION) {
            week.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun addView(view: View) {
        if (itemCount < 5) {
            val position = recycler.getChildAdapterPosition(view)
            if (position != RecyclerView.NO_POSITION) {
                week.add(itemCount, Week())
                notifyItemInserted(itemCount - 1)
            }
        }
    }
}