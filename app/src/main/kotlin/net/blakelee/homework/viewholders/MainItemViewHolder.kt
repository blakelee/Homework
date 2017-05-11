package net.blakelee.homework.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import net.blakelee.homework.R
import net.blakelee.homework.models.Classes
import org.jetbrains.anko.find

class MainItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
   var icon: ImageView = view.find(R.id.class_icon)
   val name: TextView = view.find(R.id.class_name)
   val times: TextView = view.find(R.id.class_times)

    fun init(classes: Classes) {
        icon.setImageResource(R.drawable.science)
        name.text = classes.name
        times.text = classes.weeks.getWeeksAsString()
    }
}