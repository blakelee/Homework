package net.blakelee.homework.databases

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import net.blakelee.homework.models.Exam

@Dao
interface ExamDao {

    @Insert
    fun insertExam(exam: Exam): Long

    @Query("SELECT * FROM exam WHERE id = :id")
    fun getExams(id: Long): LiveData<List<Exam>>

    @Query("SELECT * FROM exam WHERE exam_id = :exam_id LIMIT 1")
    fun getExamById(exam_id: Long): Exam

    @Delete
    fun deleteExam(exam: Exam)

    @Query("DELETE FROM exam WHERE id = :id")
    fun deleteAllExams(id: Long)

    @Update
    fun updateExam(exam: Exam)
}