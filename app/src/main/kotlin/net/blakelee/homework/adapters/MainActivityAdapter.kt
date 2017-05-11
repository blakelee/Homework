package net.blakelee.homework.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.ViewGroup
import net.blakelee.homework.R
import net.blakelee.homework.activities.ClassDetailsActivity
import net.blakelee.homework.activities.EditClassActivity
import net.blakelee.homework.databases.ClassDetailsRepository
import net.blakelee.homework.models.Classes
import net.blakelee.homework.views.items.MainItemUI
import net.blakelee.homework.viewholders.MainItemViewHolder
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.startActivity

class MainActivityAdapter(var classes: MutableList<Classes>, val ctx: Context) : RecyclerView.Adapter<MainItemViewHolder>() {

    override fun getItemCount(): Int = classes.size

    override fun onBindViewHolder(holder: MainItemViewHolder?, position: Int) {
        val id = classes[position].id

        holder?.init(classes[position])
        holder?.itemView?.setOnClickListener {
            ctx.startActivity<ClassDetailsActivity>("class_id" to id)
        }

        holder?.view?.setOnCreateContextMenuListener {
            menu: ContextMenu?, _, _ ->
            menu?.setHeaderTitle("Select action for ${holder.name.text}")
            menu?.add(R.string.action_edit)?.setOnMenuItemClickListener {
                ctx.startActivity<EditClassActivity>("class_id" to id)
                true
            }
            menu?.add(R.string.action_delete)?.setOnMenuItemClickListener {
                ClassDetailsRepository().deleteClass(id)
                classes.removeAt(position)
                notifyItemRemoved(position)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainItemViewHolder? =
        MainItemViewHolder(MainItemUI().createView(AnkoContext.create(parent!!.context, parent)))

}