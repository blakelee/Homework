package net.blakelee.homework.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import net.blakelee.homework.models.Classes
import net.blakelee.homework.views.items.MainItemUI
import net.blakelee.homework.viewholders.MainItemViewHolder
import org.jetbrains.anko.AnkoContext

class MainActivityAdapter(var classes: List<Classes>) : RecyclerView.Adapter<MainItemViewHolder>() {

    override fun getItemCount(): Int = classes.size

    override fun onBindViewHolder(holder: MainItemViewHolder?, position: Int) = holder!!.init(classes[position])

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainItemViewHolder? =
        MainItemViewHolder(MainItemUI().createView(AnkoContext.create(parent!!.context, parent)))

}