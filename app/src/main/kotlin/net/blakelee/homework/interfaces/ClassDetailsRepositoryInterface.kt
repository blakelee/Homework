package net.blakelee.homework.interfaces

import net.blakelee.homework.models.ClassDetails

interface ClassDetailsRepositoryInterface {
    fun addClass(classDetails: ClassDetails) : Boolean
    fun getClass(classId : Int?) : ClassDetails
    fun changeClass(classDetails : ClassDetails) : Boolean
    fun deleteClass(classId : Int) : Boolean
}