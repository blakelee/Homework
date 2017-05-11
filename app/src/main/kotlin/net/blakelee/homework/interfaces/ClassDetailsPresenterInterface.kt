package net.blakelee.homework.interfaces

import net.blakelee.homework.models.ClassDetails


interface ClassDetailsPresenterInterface {
    fun loadClassDetails (classId : Int) : ClassDetails
    fun onDestroy()
}