package net.blakelee.homework.interfaces

import net.blakelee.homework.models.ClassDetails

interface ClassDetailsRepositoryInterface {
    fun addClass(classDetails: ClassDetails, callback :() -> Unit)
    fun getClass(className : String, callback :(ClassDetails) -> Unit)
    fun changeClass(classDetails : ClassDetails, callback :() -> Unit)
}