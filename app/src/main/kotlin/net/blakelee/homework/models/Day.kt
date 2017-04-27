package net.blakelee.homework.models

import io.realm.RealmObject
import io.realm.annotations.Ignore
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

open class Day (
    var day : String = "None",
    var startTime : Date = Date(),
    var endTime : Date = Date()

) : RealmObject() {

    @Ignore
    var dateFormat : DateFormat = SimpleDateFormat("h:mma", Locale.getDefault())

    //This sets default startTime to "8:00AM" and endTime to "10:00AM"
    init {
        startTime = dateFormat.parse("8:00AM")
        endTime = dateFormat.parse("10:00AM")
    }


    fun getStartTime() : String = dateFormat.format(startTime)
    fun setStartTime(time : String) {
        startTime = dateFormat.parse(time)
    }

    fun getEndTime() : String = dateFormat.format(endTime)
    fun setEndTime(time : String) {
        endTime = dateFormat.parse(time)
    }

}