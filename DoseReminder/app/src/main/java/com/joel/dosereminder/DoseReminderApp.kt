package com.joel.dosereminder

import android.app.Application
import com.joel.dosereminder.di.AppContainer
import com.joel.dosereminder.di.AppDataContainer

class DoseReminderApp : Application(){

    lateinit var container: AppDataContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }


}