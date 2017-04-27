package net.blakelee.homework.interfaces

import net.blakelee.homework.models.ClassDetails
import java.util.*

interface EditClassInterface {
    fun openDaysDialog(daysSelected: List<String>, position: Int)
    fun onFinishEditDialog(daysSelected : List<String>, position : Int)
    fun openTimePicker(date : Date, compareTime: (Date) -> Int)
    fun setClassDetails(classDetails : ClassDetails)
}