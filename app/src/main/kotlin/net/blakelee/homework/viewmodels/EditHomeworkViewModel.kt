package net.blakelee.homework.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import net.blakelee.homework.databases.AppDatabase
import net.blakelee.homework.models.Homework
import net.blakelee.homework.models.Reminder
import java.util.*

class EditHomeworkViewModel(application: Application): AndroidViewModel(application) {
    private var db: AppDatabase = AppDatabase.createPersistentDatabase(application)
    var homework: MutableLiveData<Homework> = MutableLiveData()
    lateinit var reminders: LiveData<MutableList<Reminder>>

    fun loadHomeworkById(hw_id: Long, id: Long) {
        if (hw_id == -1L) {
            homework.value = Homework()
            homework.value!!.id = id
        }
        else
            homework.value = db.homeworkModel().getHomeworkById(hw_id)

        reminders = Transformations.map(homework, object : (Homework) -> MutableList<Reminder>? {
            override fun invoke(p1: Homework): MutableList<Reminder>? {
                return p1.reminders
            }
        })
    }

    fun insertHomework() = db.homeworkModel().insertHomework(homework.value!!)
    fun updateHomework() = db.homeworkModel().updateHomework(homework.value!!)
    fun addReminder() {
        if (reminders.value!!.size < 5) {
            homework.value!!.reminders.add(homework.value!!.reminders.size, Reminder())
            homework.postValue(homework.value)
        }
    }

    fun removeReminder(position: Int) {
        homework.value!!.reminders.removeAt(position)
        homework.postValue(homework.value)
    }

    fun setDay(day: Int, position: Int) {
        homework.value!!.reminders[position].day = day
        homework.postValue(homework.value)
    }

    fun setDate(date: Date) {
        homework.value!!.due = date
    }

    fun setTime(time: Date, position: Int) {
        homework.value!!.reminders[position].time = time
        homework.postValue(homework.value)
    }

    fun setType(notification: Int, position: Int) {
        homework.value!!.reminders[position].notification = notification
        homework.postValue(homework.value)
    }
}