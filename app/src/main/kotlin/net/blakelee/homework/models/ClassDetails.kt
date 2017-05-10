package net.blakelee.homework.models

import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import net.blakelee.homework.databases.AppDatabase

@Table(database = AppDatabase::class)
class ClassDetails (

        @PrimaryKey
        var name: String = "",

        var location: String = "",
        var syllabus: String = "",
        var email: String = "",
        var phone: String = "",
        var hours: Int? = null,
        var image: String = "",
        var professor: String = "",
        var finals: Day = Day()
) {

    var week: MutableList<Week> = mutableListOf(Week())
        get() {
            //week = Select().from(Week::class).where()
            return mutableListOf(Week())
        }
}
