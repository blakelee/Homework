package net.blakelee.homework.interfaces

import java.util.*

interface EditClassInterface {
    fun openDaysDialog(daysSelected: List<Int>, position: Int)
    fun onFinishEditDialog(daysSelected : List<Int>, position: Int)
    fun addWeek()
    fun removeWeek(position: Int)
    fun setStartTime(date: Date, position: Int)
    fun setEndTime(date: Date, position: Int)
    fun setIcon(id: Int)
}