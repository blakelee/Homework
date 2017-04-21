package net.blakelee.homework.models

data class Week (
        val week : ArrayList<Int> = ArrayList(7),
        val day : Day = Day()
)