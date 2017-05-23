package net.blakelee.homework.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import net.blakelee.homework.App
import net.blakelee.homework.databases.AppDatabase
import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.models.Week
import net.blakelee.homework.utils.ClassValidation
import net.blakelee.homework.utils.counter
import org.jetbrains.anko.AnkoLogger
import java.io.File

class EditClassViewModel(application: Application) : AndroidViewModel(application), AnkoLogger {
    private var db : AppDatabase = AppDatabase.createPersistentDatabase(application)

    fun insertClass(cd : ClassDetails) {
        val id = db.classModel().insertClass(cd)
        setImage(id.toString())
    }
    fun updateClass(cd : ClassDetails) {
        db.classModel().updateClass(cd)
        setImage(cd.id.toString())
    }
    fun getClass(id : Long) = db.classModel().getClassById(id)

    fun validate(cd : ClassDetails) : ClassValidation {

        if (cd.name.isEmpty())
            return ClassValidation.EMPTY

        if (db.classModel().findByName(cd.name) != null)
            return ClassValidation.CONFLICT

        for(item : Week in cd.weeks.week) {
            val week = item.day

            if (week.isEmpty())
                return ClassValidation.DAYS

            if (item.startTime > item.endTime)
                return ClassValidation.TIME
        }

        return ClassValidation.SUCCESS
    }

    private fun setImage(id : String) {
        async(CommonPool) {
            while (counter.get() > 0) { }
            val context = getApplication<App>().applicationContext
            val temp: File = File(context.filesDir, "temp")

            //Add image if one exists
            if (temp.exists()) {
                val file = File(context.filesDir, id + "-image")

                file.delete()
                temp.renameTo(file)
            }
        }
    }
}