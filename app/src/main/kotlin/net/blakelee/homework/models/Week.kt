package net.blakelee.homework.models

import android.text.format.DateUtils

data class Week (
         var day : List<Int> = listOf()
) : BaseDay() {

    fun getDayAsString() : String {
        val text = StringBuilder()

        day.let {
            for(item in it)
                text.append(DateUtils.getDayOfWeekString(item + 1, DateUtils.LENGTH_SHORTEST)) //Add 1 because I did 0-6

            if (text.isEmpty())
                return "None"

            return text.toString()
        }
    }
}