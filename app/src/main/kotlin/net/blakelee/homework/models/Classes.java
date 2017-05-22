package net.blakelee.homework.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.TypeConverters;
import net.blakelee.homework.utils.WeeksConverters;

/**
data class Classes (
    @ColumnInfo(name = "id")
    var id : Long = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "weeks")
    @TypeConverters(WeeksConverters::class)
    var weeks : Weeks = Weeks(),

    @ColumnInfo(name = "icon")
    var icon: String = ""
) {
    fun getWeeksAsString() : String {
        var output = ""
        for(week : Week in weeks.week)
            output += week.getDayAsString() + " " + week.getStartTimeAsString() + " - " + week.getEndTimeAsString() + "\n"

        return output.dropLast(1) //Drop new line
    }
}
 */

public class Classes {
    @ColumnInfo(name = "id")
    public Long id = 0L;

    @ColumnInfo(name = "name")
    public String name = "";

    @ColumnInfo(name = "weeks")
    @TypeConverters({WeeksConverters.class})
    public Weeks weeks = new Weeks();

    @ColumnInfo(name = "icon")
    public String icon = "";
}