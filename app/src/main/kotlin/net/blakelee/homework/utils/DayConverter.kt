package net.blakelee.homework.utils

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.raizlabs.android.dbflow.converter.TypeConverter
import net.blakelee.homework.models.Day
import com.raizlabs.android.dbflow.annotation.TypeConverter as TypeConverterAnnotation

@TypeConverterAnnotation
class DayConverter: TypeConverter<String, Day>() {

    @Transient
    val gson = Gson()

    override fun getDBValue(day: Day?): String {
        return gson.toJson(day)
    }

    override fun getModelValue(day: String): Day {
        return gson.fromJson<Day>(day)
    }
}