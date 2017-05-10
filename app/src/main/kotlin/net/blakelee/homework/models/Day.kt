package net.blakelee.homework.models

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.Table
import net.blakelee.homework.databases.AppDatabase
import java.util.*

@Table(database = AppDatabase::class)
open class Day (
        @Column
       var day : Date? = null
) : BaseDay() {

    fun  getDay(): String {
        day?.let {
            return day.toString()
        }

        return "None"
    }
}