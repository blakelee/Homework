package net.blakelee.homework.models

data class Week (
        var week : ArrayList<Int> = ArrayList(7),
        var day : Day = Day()
)