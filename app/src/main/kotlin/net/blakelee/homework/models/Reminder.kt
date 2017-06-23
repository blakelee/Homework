package net.blakelee.homework.models

import net.blakelee.homework.utils.getHour
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Reminder {
    var day: Int? = 1           //How many days before due to notify
    var time: Date = getHour(10, 0)    //Minutes and hours of when to notify
    var notification: Int = 0   //Type of notification e.g. vibrate, statusbar, loud

    @Transient
    var dateFormat : DateFormat = SimpleDateFormat("h:mma", Locale.getDefault())

    fun getTimeAsString(): String = dateFormat.format(time)
}