package net.blakelee.homework.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import net.blakelee.homework.databases.AppDatabase
import net.blakelee.homework.models.Exam

class ExamViewModel(application: Application): AndroidViewModel(application) {
    private var db: AppDatabase = AppDatabase.createPersistentDatabase(application)

    fun getExams(id: Long): LiveData<List<Exam>> = db.examModel().getExams(id)
    fun deleteExam(exam: Exam) = db.examModel().deleteExam(exam)
}