package net.blakelee.homework.utils

import android.arch.persistence.room.TypeConverter
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import net.blakelee.homework.models.Weeks

class WeeksConverters {
        @TypeConverter
        fun toString(weeks: Weeks) : String = Gson().toJson(weeks)

        @TypeConverter
        fun toWeeks(weeks: String) : Weeks = Gson().fromJson<Weeks>(weeks)
}