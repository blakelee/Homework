package net.blakelee.homework

import android.app.Application
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import net.blakelee.homework.databases.AppDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabase.createPersistentDatabase(this)
        Stetho.initializeWithDefaults(this)
        if (LeakCanary.isInAnalyzerProcess(this))
            return
        LeakCanary.install(this)
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}