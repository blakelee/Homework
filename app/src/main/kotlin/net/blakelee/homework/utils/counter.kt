package net.blakelee.homework.utils

object counter {
    var counter : Int = 0
    fun increment() = counter++
    fun decrement() = counter--
    fun get() = counter
}