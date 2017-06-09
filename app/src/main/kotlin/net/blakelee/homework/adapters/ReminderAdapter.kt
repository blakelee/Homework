package net.blakelee.homework.adapters

import android.view.View
import android.view.ViewGroup
import net.blakelee.homework.base.BaseAdapter
import net.blakelee.homework.base.BaseViewHolder
import net.blakelee.homework.models.Reminder
import net.blakelee.homework.views.items.ReminderItemUI
import org.jetbrains.anko.AnkoContext

class ReminderAdapter: BaseAdapter<Reminder, ReminderAdapter.ReminderViewHolder>() {

    override fun getItemViewId(): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ReminderViewHolder =
    ReminderViewHolder(ReminderItemUI().createView(AnkoContext.create(parent!!.context, parent)))

    override fun instantiateViewHolder(view: View?): ReminderViewHolder =
            ReminderViewHolder(view)

    class ReminderViewHolder(view: View?): BaseViewHolder<Reminder>(view) {

        override fun onBind(item: Reminder) {

        }
    }
}