package net.blakelee.homework.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "exam")
class Exam: BaseAssignment() {

    @PrimaryKey(autoGenerate = true)
    var exam_id: Long? = null
}
