package net.blakelee.homework.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import net.blakelee.homework.databases.AppDatabase
import net.blakelee.homework.models.Classes

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var db : AppDatabase = AppDatabase.createPersistentDatabase(application)

    fun getClasses() : LiveData<List<Classes>> = db.classModel().getClasses()

    fun deleteClass(id: Long) {
        db.classModel().deleteClass(id)

        //TODO: Need to delete images here
    }
}