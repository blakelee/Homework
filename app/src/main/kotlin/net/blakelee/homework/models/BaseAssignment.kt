package net.blakelee.homework.models

import android.arch.persistence.room.Ignore
import android.arch.persistence.room.TypeConverters
import net.blakelee.homework.utils.DateConverter
import net.blakelee.homework.utils.ReminderConverter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

open class BaseAssignment {
    var id: Long = 0
    var title: String = ""
    var description: String = ""

    @TypeConverters(DateConverter::class)
    var due: Date? = Date(2017, 5, 12)

    @TypeConverters(ReminderConverter::class)
    var reminders: MutableList<Reminder> = mutableListOf()

    @Ignore
    var time : DateFormat = SimpleDateFormat("h:mma", Locale.getDefault())

    @Ignore
    var day: DateFormat = SimpleDateFormat("EE", Locale.getDefault())

    fun getTime(): String = time.format(due)
    fun getDay(): String = day.format(due)
}