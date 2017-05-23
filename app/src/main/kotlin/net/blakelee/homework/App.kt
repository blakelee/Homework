package net.blakelee.homework

import android.app.Application
import com.facebook.stetho.Stetho
import net.blakelee.homework.databases.AppDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabase.createPersistentDatabase(this)
        Stetho.initializeWithDefaults(this)
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}