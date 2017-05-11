package net.blakelee.homework.utils

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.raizlabs.android.dbflow.converter.TypeConverter
import net.blakelee.homework.models.Weeks
import com.raizlabs.android.dbflow.annotation.TypeConverter as TypeConverterAnnotation

@TypeConverterAnnotation
class WeeksConverter: TypeConverter<String, Weeks>() {

    @Transient
    val gson = Gson()

    override fun getDBValue(weeks: Weeks?): String {
        return gson.toJson(weeks)
    }

    override fun getModelValue(weeks: String): Weeks {
        return gson.fromJson<Weeks>(weeks)
    }
}