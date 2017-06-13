package net.blakelee.homework.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import net.blakelee.homework.R
import net.blakelee.homework.base.BaseAdapter
import net.blakelee.homework.base.BaseViewHolder
import net.blakelee.homework.interfaces.OnLongClickListener
import net.blakelee.homework.models.Homework
import net.blakelee.homework.views.items.AssignmentItemUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class HomeworkAdapter(val bg_color: Int, val longClick: OnLongClickListener): BaseAdapter<Homework, HomeworkAdapter.HomeworkViewHolder>() {

    override fun getItemViewId(): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeworkViewHolder =
        HomeworkViewHolder(AssignmentItemUI().createView(AnkoContext.create(parent!!.context, parent)), bg_color, longClick)

    override fun instantiateViewHolder(view: View?): HomeworkViewHolder =
        HomeworkViewHolder(view, bg_color, longClick)

    class HomeworkViewHolder(itemView: View?, val bg_color: Int,  val onLongClickListener: OnLongClickListener) : BaseViewHolder<Homework>(itemView) {
        val date = itemView!!.find<TextView>(R.id.date)
        val time = itemView!!.find<TextView>(R.id.time)
        val name = itemView!!.find<TextView>(R.id.name)
        val details = itemView!!.find<TextView>(R.id.details)
        val color = itemView!!.find<LinearLayout>(R.id.color)

        override fun onBind(item: Homework) {
            date.text = item.getDay()
            time.text = item.getTime()
            color.setBackgroundColor(bg_color)
            details.text = item.description
            name.text = item.title
            itemView.setOnLongClickListener {
                onLongClickListener.onLongClick(adapterPosition)
                true
            }
        }
    }
}