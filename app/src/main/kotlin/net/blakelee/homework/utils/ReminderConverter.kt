package net.blakelee.homework.utils

import android.arch.persistence.room.TypeConverter
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import net.blakelee.homework.models.Reminder

class ReminderConverter {
    @TypeConverter
    fun toString(reminder: MutableList<Reminder>) : String = Gson().toJson(reminder)

    @TypeConverter
    fun toReminder(reminder: String) : MutableList<Reminder> = Gson().fromJson<MutableList<Reminder>>(reminder)
}