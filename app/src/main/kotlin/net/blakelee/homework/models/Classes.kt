package net.blakelee.homework.models

import android.arch.persistence.room.TypeConverters
import net.blakelee.homework.utils.WeeksConverters

class Classes {
    var id : Long = 0
    var name: String = ""

    @TypeConverters(WeeksConverters::class)
    var weeks : Weeks = Weeks()
    var icon: String = ""
}