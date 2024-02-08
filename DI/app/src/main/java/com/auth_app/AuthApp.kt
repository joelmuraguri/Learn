package com.auth_app

import android.app.Application
import com.auth_app.authentication_app.di.AppContainer
import com.auth_app.authentication_app.di.AppDataContainer

class AuthApp : Application() {

    lateinit var appDataContainer: AppDataContainer

    override fun onCreate() {
        super.onCreate()
        appDataContainer = AppContainer()
    }

}