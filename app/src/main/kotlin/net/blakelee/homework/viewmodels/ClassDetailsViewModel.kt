package net.blakelee.homework.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import net.blakelee.homework.databases.AppDatabase

class ClassDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private var db : AppDatabase = AppDatabase.getPersistentDatabase(application)

    fun getClass(id : Long) = db.classModel().getClassesById(id)
}