package net.blakelee.homework.interfaces

import android.view.View

interface EditClassInterface {
    fun openDaysDialog(view: View, daysSelected: ArrayList<Int>)
    fun onFinishEditDialog(daysSelected : ArrayList<Int>)
}