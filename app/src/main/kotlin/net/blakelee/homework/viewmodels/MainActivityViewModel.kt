package net.blakelee.homework.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import net.blakelee.homework.App
import net.blakelee.homework.databases.AppDatabase
import net.blakelee.homework.models.Classes
import java.io.File

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var db : AppDatabase = AppDatabase.createPersistentDatabase(application)

    fun getClasses() : LiveData<List<Classes>> = db.classModel().getClasses()

    fun deleteClass(id: Long) {
        db.classModel().deleteClass(id)

        async(CommonPool) {
            val context = getApplication<App>().applicationContext
            val file = File(context.filesDir, id.toString() + "-image")
            file.delete()
        }
    }
}