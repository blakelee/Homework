package net.blakelee.homework.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
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
import java.util.*

class EditClassViewModel(application: Application) : AndroidViewModel(application), AnkoLogger {
    private var db: AppDatabase = AppDatabase.createPersistentDatabase(application)
    var classDetails: MutableLiveData<ClassDetails> = MutableLiveData()
    var weeks = MediatorLiveData<MutableList<Week>>()

    fun insertClass() {
        val id = db.classModel().insertClass(classDetails.value!!)
        setImage(id.toString())
    }

    fun updateClass() {
        db.classModel().updateClass(classDetails.value!!)
        setImage(classDetails.value!!.id.toString())
    }

    fun loadClassById(id: Long?) {
        if (id == null)
            classDetails.value = ClassDetails()
        else
            classDetails.value = db.classModel().getClassById(id)

        weeks.addSource(classDetails) {
            it?.let { weeks.value = it.weeks.week }
        }
    }

    fun validate() : ClassValidation {

        if (classDetails.value!!.name.isEmpty())
            return ClassValidation.EMPTY

        if (db.classModel().findByName(classDetails.value!!.name) != null)
            return ClassValidation.CONFLICT

        for(item : Week in classDetails.value!!.weeks.week) {
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

    fun addWeek() = weeks.value!!.add(weeks.value!!.size, Week())
    fun removeWeek(position: Int) = weeks.value!!.removeAt(position)

    fun setDay(daysSelected: List<Int>, position: Int) {
        weeks.value!![position].day = daysSelected
    }

    fun setStartTime(date: Date, position: Int) {
        classDetails.value!!.weeks.week[position].startTime = date
    }

    fun setEndTime(date: Date, position: Int) {
        classDetails.value!!.weeks.week[position].endTime = date
    }
}