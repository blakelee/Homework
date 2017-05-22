package net.blakelee.homework.databases

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import net.blakelee.homework.models.ClassDetails

@Database(entities = arrayOf(ClassDetails::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun classModel() : ClassDao

    companion object {
        private const val NAME = "app.db"
        private var instance : AppDatabase? = null

        fun getPersistentDatabase(context : Context) : AppDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, NAME)
                    .allowMainThreadQueries()
                    .build()

            return instance!!
        }

        fun getInMemoryDatabase(context: Context) : AppDatabase {
            if (instance == null)
            instance = Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
                    .allowMainThreadQueries()
                    .build()

            return instance!!
        }

        fun destroyInstance() { instance = null }
    }
}