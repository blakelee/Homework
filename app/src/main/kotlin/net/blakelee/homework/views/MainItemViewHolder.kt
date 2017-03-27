package net.blakelee.homework.views

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.find
import net.blakelee.homework.R
import net.blakelee.homework.models.Classes


class MainItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
    var icon: ImageView = view.find(R.id.class_icon)
    val name: TextView = view.find(R.id.class_name)
    val times: TextView = view.find(R.id.class_times)

    fun init(classes: Classes) {
        icon.setImageDrawable(Drawable.createFromPath(classes.toString()))
        name.text = classes.name
        times.text = classes.times
    }
}