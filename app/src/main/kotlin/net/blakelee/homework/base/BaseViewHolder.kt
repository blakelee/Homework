package net.blakelee.homework.base

import android.support.v7.widget.RecyclerView
import android.view.View

//This code written by Ihor Kucherenko as part of Android-Architecture-Components

abstract class BaseViewHolder<D>(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(item: D)
}