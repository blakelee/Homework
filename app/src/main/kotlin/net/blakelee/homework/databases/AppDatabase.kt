package net.blakelee.homework.databases

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.models.Exam
import net.blakelee.homework.models.Homework

@Database(entities = arrayOf(ClassDetails::class, Homework::class, Exam::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun classModel() : ClassDao
    abstract fun homeworkModel(): HomeworkDao
    abstract fun examModel(): ExamDao
    abstract fun noteModel(): NoteDao

    companion object {
        private const val NAME = "app.db"
        private var instance : AppDatabase? = null

        fun createInMemoryDatabase(context : Context) : AppDatabase {
            if (instance == null)
                instance = Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
                        .allowMainThreadQueries()
                        .build()

            return instance!!
        }

        fun createPersistentDatabase(context: Context) : AppDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, NAME)
                    .allowMainThreadQueries()
                    .build()

            return instance!!
        }

        fun destroyInstance() { instance = null }
    }
}