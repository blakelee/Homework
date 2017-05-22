package net.blakelee.homework.interfaces

import net.blakelee.homework.models.ClassDetails
import java.util.*

interface EditClassInterface {
    fun openDaysDialog(daysSelected: List<Int>, position: Int)
    fun onFinishEditDialog(daysSelected : List<Int>, position : Int)
    fun openTimePicker(date : Date, set: (Date) -> Unit)
    fun setClassDetails(classDetails : ClassDetails)
}