package net.blakelee.homework.models

import java.util.*

class Reminder {
    var day: Int? = 0           //How many days before due to notify
    var time: Date? = null      //Minutes and hours of when to notify
    var notification: Int = 0   //Type of notification e.g. vibrate, statusbar, loud
}