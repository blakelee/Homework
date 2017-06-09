package net.blakelee.homework.adapters

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import net.blakelee.homework.R
import net.blakelee.homework.activities.ClassDetailsActivity
import net.blakelee.homework.base.BaseAdapter
import net.blakelee.homework.base.BaseViewHolder
import net.blakelee.homework.interfaces.MainInterface
import net.blakelee.homework.models.Classes
import net.blakelee.homework.views.items.MainItemUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class ClassesAdapter(val mi: MainInterface) : BaseAdapter<Classes, ClassesAdapter.ClassesViewHolder>() {

    override fun getItemViewId(): Int = 0

    override fun instantiateViewHolder(view: View?): ClassesViewHolder = ClassesViewHolder(view, mi)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ClassesViewHolder =
        ClassesAdapter.ClassesViewHolder(MainItemUI().createView(AnkoContext.create(parent!!.context, parent)), mi)

    class ClassesViewHolder(val view: View?, val mi: MainInterface) : BaseViewHolder<Classes>(view) {
        val icon: ImageView = view!!.find<ImageView>(R.id.class_icon)
        val name: TextView = view!!.find<TextView>(R.id.class_name)
        val times: TextView = view!!.find<TextView>(R.id.class_times)
        var id: Long = 0

        override fun onBind(item: Classes) {
            icon.setImageResource(item.icon)
            icon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
            icon.setBackgroundColor(item.icon_color)
            name.text = item.name
            times.text = item.getWeeksAsString()
            id = item.id

            itemView.setOnClickListener { itemView.context.startActivity<ClassDetailsActivity>("class_id" to id, "color" to item.icon_color) }
            itemView.setOnLongClickListener { mi.showMenu(item) }
        }
    }
}