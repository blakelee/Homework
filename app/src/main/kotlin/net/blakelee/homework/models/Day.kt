package net.blakelee.homework.models

import java.text.DateFormat
import java.util.*

data class Day (
       var day : Date? = null
) : BaseDay() {

    fun  getDay(): String {
        day?.let {
            val fixedDay : Date = day!!.clone() as Date
            fixedDay.year = fixedDay.year - 1900 //Java.time sucks so freaking bad
            return DateFormat.getDateInstance(DateFormat.LONG).format(fixedDay)
        }

        return "None"
    }
}