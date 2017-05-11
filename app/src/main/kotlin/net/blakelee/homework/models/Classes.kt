package net.blakelee.homework.models

import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import net.blakelee.homework.databases.AppDatabase

@Table(database = AppDatabase::class, allFields = true)
class Classes (

        @PrimaryKey
        var id: Int = 0,
        var name: String = "",
        var weeks: Weeks = Weeks(),
        var icon: String = ""
) : BaseModel()