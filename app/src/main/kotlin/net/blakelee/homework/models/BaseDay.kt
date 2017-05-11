package net.blakelee.homework.models

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

open class BaseDay {

    @Transient
    var dateFormat : DateFormat = SimpleDateFormat("h:mma", Locale.getDefault())

    var startTime : Date = dateFormat.parse("8:00AM")
    var endTime : Date = dateFormat.parse("10:00AM")

    fun getStartTime() : String = dateFormat.format(startTime)
    fun setStartTime(time : String) {
        startTime = dateFormat.parse(time)
    }

    fun getEndTime() : String = dateFormat.format(endTime)
    fun setEndTime(time : String) {
        endTime = dateFormat.parse(time)
    }
}