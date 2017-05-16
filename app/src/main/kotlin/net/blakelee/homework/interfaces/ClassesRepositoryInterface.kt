package net.blakelee.homework.interfaces

import net.blakelee.homework.models.Classes

interface ClassesRepositoryInterface {
    fun addClass(classes: Classes) : Long
    fun getClasses() : MutableList<Classes>
    fun changeClass(classes: Classes) : Boolean
    fun getClass(classId: Long) : Classes?
}