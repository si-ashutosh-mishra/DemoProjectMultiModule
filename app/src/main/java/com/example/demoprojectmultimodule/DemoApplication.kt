package com.example.demoprojectmultimodule

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DemoApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        //for networking debugging
        if (true) {
            Stetho.initializeWithDefaults(this)
        }
    }
}