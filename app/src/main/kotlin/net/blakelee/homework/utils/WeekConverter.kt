package net.blakelee.homework.utils

import android.arch.persistence.room.TypeConverter
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import net.blakelee.homework.models.Week

class WeekConverter {
        @TypeConverter
        fun toString(weeks: MutableList<Week>) : String = Gson().toJson(weeks)

        @TypeConverter
        fun toWeeks(weeks: String) : MutableList<Week> = Gson().fromJson<MutableList<Week>>(weeks)
}