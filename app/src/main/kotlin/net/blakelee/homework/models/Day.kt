package net.blakelee.homework.models

import java.util.*
open class Day (
       var day : Date? = null
) : BaseDay() {

    fun  getDay(): String {
        day?.let {
            return day.toString()
        }

        return "None"
    }
}