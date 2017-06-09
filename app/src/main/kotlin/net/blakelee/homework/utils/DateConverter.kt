package net.blakelee.homework.utils

import android.arch.persistence.room.TypeConverter
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import java.util.*


class DateConverter {
    @TypeConverter
    fun toString(date: Date?): String = Gson().toJson(date)

    @TypeConverter
    fun toWeeks(date: String): Date? = Gson().fromJson<Date>(date)
}