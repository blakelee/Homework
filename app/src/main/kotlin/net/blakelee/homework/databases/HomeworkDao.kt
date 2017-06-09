package net.blakelee.homework.databases

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import net.blakelee.homework.models.Homework

@Dao
interface HomeworkDao {

    @Insert
    fun insertHomework(hw: Homework): Long

    @Query("SELECT * FROM homework WHERE id = :id")
    fun getHomework(id: Long): LiveData<List<Homework>>

    @Query("SELECT * FROM homework WHERE hw_id = :hw_id LIMIT 1")
    fun getHomeworkById(hw_id: Long): Homework

    @Delete
    fun deleteHomework(hw: Homework)

    @Update
    fun updateHomework(hw: Homework)
}