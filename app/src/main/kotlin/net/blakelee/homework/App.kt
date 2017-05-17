package net.blakelee.homework

import android.app.Application
import com.facebook.stetho.Stetho
import com.raizlabs.android.dbflow.config.FlowManager
import java.util.concurrent.atomic.AtomicInteger

class App : Application() {

    var counter = AtomicInteger()

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        FlowManager.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        FlowManager.destroy()
    }
}