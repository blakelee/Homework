package net.blakelee.homework.models

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import net.blakelee.homework.R
import net.blakelee.homework.activities.ClassDetailsActivity
import org.jetbrains.anko.find

class MainItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
   var icon: ImageView = view.find(R.id.class_icon)
   val name: TextView = view.find(R.id.class_name)
   val times: TextView = view.find(R.id.class_times)

    fun init(classes: Classes) {
        icon.setImageResource(R.drawable.science)
        name.text = classes.name
        var s : String = ""
        for(s2: String in classes.getTimes())
            s = s + s2 + "\n"

        s = s.dropLast(1)
        times.text = s

        itemView.setOnClickListener {
            //TODO: set bundle to name of class instead of passing dummy data
            itemView?.context?.startActivity(Intent(itemView.context, ClassDetailsActivity().javaClass))
        }
    }
}