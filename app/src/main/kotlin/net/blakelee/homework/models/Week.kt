package net.blakelee.homework.models

import io.realm.RealmObject

open class Week (
    open var week: String = "",
    open var day : Day = Day()

) : RealmObject() {

    fun getWeek() : List<String> {
        val week = week.split(",,,,")
        if (week[0] == "")
            return listOf()
        return week
    }

    fun setWeek(weeks : List<String>?) {
        if (weeks == null)
            week = ""
        else
            week = weeks.joinToString(",,,,")
    }
}