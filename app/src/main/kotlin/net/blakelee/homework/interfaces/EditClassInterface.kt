package net.blakelee.homework.interfaces

import android.view.View

interface EditClassInterface {
    fun openDaysDialog(daysSelected: ArrayList<Int>)
    fun onFinishEditDialog(daysSelected : ArrayList<Int>)
}