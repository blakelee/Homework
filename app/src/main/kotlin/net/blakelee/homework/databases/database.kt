package net.blakelee.homework.databases

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/*class database(ctx : Context) : ManagedSQLiteOpenHelper(ctx, "classes", null, 1) {
    companion object {
        private var instance: database? = null

        @Synchronized
        fun getInstance(ctx: Context): database {
            if (instance == null) {
                instance = database(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        /*db.createTable("classMain", ifNotExists = true,
                "name" to BLOB + PRIMARY_KEY + UNIQUE,
                "icon" to BLOB)*/
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db?.dropTable("User", true)
    }
}*/