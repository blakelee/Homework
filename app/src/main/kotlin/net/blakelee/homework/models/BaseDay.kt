package net.blakelee.homework.models

import net.blakelee.homework.utils.getHour
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

open class BaseDay {

    @Transient
    var dateFormat : DateFormat = SimpleDateFormat("h:mma", Locale.getDefault())

    var startTime : Date = getHour(8, 0)
    var endTime : Date = getHour(10,0)

    fun getStartTimeAsString() : String = dateFormat.format(startTime)
    fun setStartTime(time : String) {
        startTime = dateFormat.parse(time)
    }

    fun getEndTimeAsString() : String = dateFormat.format(endTime)
    fun setEndTime(time : String) {
        endTime = dateFormat.parse(time)
    }
}