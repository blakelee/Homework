package net.blakelee.homework.interfaces

import net.blakelee.homework.models.Classes

interface MainActivityPresenterInterface {
    fun loadClasses() : MutableList<Classes>
    fun onDestroy()
}