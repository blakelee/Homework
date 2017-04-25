package net.blakelee.homework.interfaces

interface EditClassInterface {
    fun openDaysDialog(daysSelected: ArrayList<Int>, position: Int)
    fun onFinishEditDialog(daysSelected : ArrayList<Int>, position : Int)
    fun openTimePicker(time: String, compareTime: (String) -> Int)
}