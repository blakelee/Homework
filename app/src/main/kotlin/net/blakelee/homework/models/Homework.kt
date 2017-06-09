package net.blakelee.homework.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "homework")
class Homework: BaseAssignment() {

    @PrimaryKey(autoGenerate = true)
    var hw_id: Long? = null

    var problems: String = ""
}