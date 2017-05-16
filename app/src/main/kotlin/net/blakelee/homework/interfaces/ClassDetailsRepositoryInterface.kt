package net.blakelee.homework.interfaces

import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.models.Classes

interface ClassDetailsRepositoryInterface {
    fun insertClass(classDetails: ClassDetails) : Boolean
    fun getClass(classId : Long?) : ClassDetails?
    fun changeClass(classDetails : ClassDetails) : Boolean
    fun deleteClass(classId : Long)
    fun getClasses() : MutableList<Classes>?
}