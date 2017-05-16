package net.blakelee.homework.models

import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import net.blakelee.homework.databases.AppDatabase

@Table(database = AppDatabase::class, allFields = true)
class ClassDetails (

        @PrimaryKey
        var id : Long = 0,
        var name: String = "",
        var weeks : Weeks = Weeks(),
        var location: String = "",
        var syllabus: String = "",
        var email: String = "",
        var phone: String = "",
        var hours: Int? = null,
        var professor: String = "",
        var icon: String = "",
        var ringmode: Int? = null
) : BaseModel()
