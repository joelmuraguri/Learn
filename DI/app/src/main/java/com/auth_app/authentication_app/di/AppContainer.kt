package com.auth_app.authentication_app.di

import com.auth_app.authentication_app.data.AccountService
import com.auth_app.authentication_app.data.AuthRepository
import com.google.firebase.auth.FirebaseAuth

interface AppDataContainer{
    val accountService : AccountService
}

class AppContainer : AppDataContainer{

    override val accountService: AccountService by lazy {
        AuthRepository(FirebaseAuth.getInstance())
    }

}