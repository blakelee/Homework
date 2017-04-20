package net.blakelee.homework.models

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.find
import net.blakelee.homework.R


class MainItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
    var icon: ImageView = view.find(R.id.class_icon)
    val name: TextView = view.find(R.id.class_name)
    val times: TextView = view.find(R.id.class_times)

    fun init(classes: Classes) {
        icon.setImageDrawable(Drawable.createFromPath(classes.toString()))
        name.text = classes.name

        var s : String = ""
        for(s2: String in classes.times) {
            s = s + s2 + "\n"
        }

    }
}