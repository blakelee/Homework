package net.blakelee.homework.models

import android.arch.persistence.room.TypeConverters
import net.blakelee.homework.utils.WeekConverter

class Classes {
    var id : Long = 0
    var name: String = ""

    @TypeConverters(WeekConverter::class)
    var weeks : MutableList<Week> = mutableListOf()
    var icon: Int = 0
    var icon_color: Int = 0

    fun getWeeksAsString() : String {
        var output = ""
        for(week in this.weeks)
            output += week.getDayAsString() + " " + week.getStartTimeAsString() + " - " + week.getEndTimeAsString() + "\n"

        return output.dropLast(1) //Drop new line
    }
}