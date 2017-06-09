package net.blakelee.homework.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import net.blakelee.homework.databases.AppDatabase
import net.blakelee.homework.models.Homework

class HomeworkViewModel(application: Application): AndroidViewModel(application) {
    private var db: AppDatabase = AppDatabase.createPersistentDatabase(application)

    fun getHomework(id: Long): LiveData<List<Homework>> = db.homeworkModel().getHomework(id)
    fun deleteHomework(hw: Homework) = db.homeworkModel().deleteHomework(hw)
}