package net.blakelee.homework.interfaces

import net.blakelee.homework.models.ClassDetails


interface EditClassPresenterInterface {
    fun onGetClassSuccess(classDetails : ClassDetails)
    fun onChangeClassSuccess()
    fun onAddClassSuccess()
    fun onDestroy()
}