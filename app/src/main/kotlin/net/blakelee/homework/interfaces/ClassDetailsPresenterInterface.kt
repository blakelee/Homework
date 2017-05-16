package net.blakelee.homework.interfaces

import net.blakelee.homework.models.ClassDetails


interface ClassDetailsPresenterInterface {
    fun loadClassDetails (classId : Long) : ClassDetails?
    fun onDestroy()
}