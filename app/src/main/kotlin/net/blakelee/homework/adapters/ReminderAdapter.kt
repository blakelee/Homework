package net.blakelee.homework.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import net.blakelee.homework.R
import net.blakelee.homework.base.BaseAdapter
import net.blakelee.homework.base.BaseViewHolder
import net.blakelee.homework.models.Reminder
import net.blakelee.homework.views.items.ReminderItemUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class ReminderAdapter(val remove: (Int) -> Unit, val type: (Int, Int) -> Unit, val time: (Int) -> Unit): BaseAdapter<Reminder, ReminderAdapter.ReminderViewHolder>() {

    override fun getItemViewId(): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ReminderViewHolder =
    ReminderViewHolder(ReminderItemUI().createView(AnkoContext.create(parent!!.context, parent)))

    override fun instantiateViewHolder(view: View?): ReminderViewHolder =
            ReminderViewHolder(view)

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        holder.onBind(getItem(position))

        holder.remove.setOnClickListener { remove(position) }
        holder.time_picker.setOnClickListener { time(position) }
        holder.notification_picker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, item: Int, id: Long) {
                type(item, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    class ReminderViewHolder(view: View?): BaseViewHolder<Reminder>(view) {
        val day_picker = itemView!!.find<EditText>(R.id.days)
        val time_picker = itemView!!.find<Button>(R.id.time)
        val notification_picker = itemView!!.find<Spinner>(R.id.type)
        val remove = itemView!!.find<Button>(R.id.remove)

        override fun onBind(item: Reminder) {
            item.day?.let { day_picker.setText(item.day.toString()) }
            time_picker.text = item.getTimeAsString()
            notification_picker.setSelection(item.notification)
        }
    }
}