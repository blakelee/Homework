package net.blakelee.homework.interfaces

import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.models.Week

interface ClassDetailsRepositoryInterface {
    fun addClass(classDetails: ClassDetails)
    fun getClass(className : String)
    fun changeClass(classDetails : ClassDetails)
    fun getWeek(className: String) : MutableList<Week>
}