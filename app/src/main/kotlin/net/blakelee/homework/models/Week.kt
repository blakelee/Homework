package net.blakelee.homework.models

import android.text.format.DateUtils
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.Table
import net.blakelee.homework.databases.AppDatabase

@Table(database = AppDatabase::class)
open class Week (
        @Column
         var day : List<Int> = listOf()
) : BaseDay() {

    fun getDay() : String {
        val text = StringBuilder()

        day.let {
            for(item in it)
                text.append(DateUtils.getDayOfWeekString(item + 1, DateUtils.LENGTH_SHORTEST)) //Add 1 because I did 0-6

            if (text.isEmpty())
                return "None"

            return text.toString()
        }
    }
}