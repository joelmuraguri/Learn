package com.joel.composeflix

import android.app.Application
import timber.log.Timber

class ComposeFlixApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }

}