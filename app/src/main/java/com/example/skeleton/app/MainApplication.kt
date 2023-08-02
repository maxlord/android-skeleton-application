package com.example.skeleton.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initThreeTen()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initThreeTen() {
        AndroidThreeTen.init(this)
    }
}