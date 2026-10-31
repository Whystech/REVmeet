package org.ianus.revmeet.main

import android.app.Application
import timber.log.Timber.i
import timber.log.Timber

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Placemark started")
    }
}