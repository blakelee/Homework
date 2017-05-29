package net.blakelee.homework.interfaces

import net.blakelee.homework.models.Classes

interface MainInterface {
    fun showMenu(item: Classes) : Boolean
}