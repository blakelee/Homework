package net.blakelee.homework.adapters

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

class ClassesAdapter(private val mainInterface: MainInterface) : BaseAdapter<Classes, ClassesAdapter.ClassesViewHolder>() {

    override fun getItemViewId(): Int = R.id.classList

    override fun instantiateViewHolder(view: View?): ClassesViewHolder = ClassesViewHolder(view)

    override fun onBindViewHolder(holder: ClassesViewHolder, position: Int) {
        holder.onBind(getItem(position))
        val item = holder.itemView
        val id = holder.id

        item.setOnClickListener {
            item.context.startActivity<ClassDetailsActivity>("class_id" to id)
        }

        item.setOnLongClickListener {
            mainInterface.showMenu(holder.name.text.toString(), id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ClassesViewHolder =
        ClassesAdapter.ClassesViewHolder(MainItemUI().createView(AnkoContext.create(parent!!.context, parent)))

    class ClassesViewHolder(view: View?) : BaseViewHolder<Classes>(view) {
        val icon: ImageView = view!!.find<ImageView>(R.id.class_icon)
        val name: TextView = view!!.find<TextView>(R.id.class_name)
        val times: TextView = view!!.find<TextView>(R.id.class_times)
        var id: Long = 0

        override fun onBind(item: Classes) {
            icon.setImageResource(R.drawable.science)
            name.text = item.name
            times.text = item.weeks.getWeeksAsString()
            id = item.id
        }
    }
}