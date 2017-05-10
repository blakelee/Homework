package net.blakelee.homework.models

import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import net.blakelee.homework.databases.AppDatabase

//TODO: Change to null since this is just dummy data
@Table(database = AppDatabase::class)
open class Classes (

        @PrimaryKey
        var name: String = "Psychology 101",

        var times: List<String> = listOf("MWF 8:00am-10:00am", "TT 10:00am-11:00am", "S 12:00pm-1:00pm"),
        var icon: String = ""
)