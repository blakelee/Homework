package net.blakelee.homework.models

import android.arch.persistence.room.TypeConverters
import net.blakelee.homework.utils.Date
import net.blakelee.homework.utils.DateConverter
import net.blakelee.homework.utils.ReminderConverter
import net.blakelee.homework.utils.trim
import java.text.SimpleDateFormat
import java.util.*

open class BaseAssignment {
    var id: Long = 0
    var title: String = ""
    var description: String = ""

    @TypeConverters(DateConverter::class)
    var due: Date = Date(10, 0)

    @TypeConverters(ReminderConverter::class)
    var reminders: MutableList<Reminder> = mutableListOf()

    fun getTime(): String = SimpleDateFormat("h:mma", Locale.getDefault()).format(due)

    fun getDay(long: Boolean = false): String {

        val start = Calendar.getInstance()
        start.trim()

        val mid = Calendar.getInstance()
        mid.time = due

        val end = start.clone() as Calendar
        end.add(Calendar.DATE, 1)

        if (mid in start..end) {
            if (long)
                return "Today"
            else
                return "Tod"
        }

        end.add(Calendar.DATE, 1)

        if (mid in start..end) {
            if (long)
                return "Tomorrow"
            else
                return "Tom"
        }

        end.add(Calendar.DATE, 5)

        if (mid in start..end) {
            if (long)
                return SimpleDateFormat("EEEE", Locale.getDefault()).format(due)
            else
                return SimpleDateFormat("EE", Locale.getDefault()).format(due)
        }

        if (long)
            return SimpleDateFormat("M/dd/yyyy", Locale.getDefault()).format(due)
        else
            return SimpleDateFormat("M/dd", Locale.getDefault()).format(due)
    }
}