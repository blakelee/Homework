package net.blakelee.homework.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import net.blakelee.homework.R
import net.blakelee.homework.base.BaseAdapter
import net.blakelee.homework.base.BaseViewHolder
import net.blakelee.homework.interfaces.OnLongClickListener
import net.blakelee.homework.models.Exam
import net.blakelee.homework.views.items.AssignmentItemUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find


class ExamAdapter(val bg_color: Int, val longClick: OnLongClickListener): BaseAdapter<Exam, ExamAdapter.ExamViewHolder>() {

    override fun getItemViewId(): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ExamViewHolder =
            ExamViewHolder(AssignmentItemUI().createView(AnkoContext.create(parent!!.context, parent)), bg_color, longClick)

    override fun instantiateViewHolder(view: View?): ExamViewHolder =
            ExamViewHolder(view, bg_color, longClick)

    class ExamViewHolder(itemView: View?, val bg_color: Int, val onLongClickListener: OnLongClickListener) : BaseViewHolder<Exam>(itemView) {
        val date = itemView!!.find<TextView>(R.id.date)
        val time = itemView!!.find<TextView>(R.id.time)
        val name = itemView!!.find<TextView>(R.id.name)
        val details = itemView!!.find<TextView>(R.id.details)
        val color = itemView!!.find<LinearLayout>(R.id.color)

        override fun onBind(item: Exam) {
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