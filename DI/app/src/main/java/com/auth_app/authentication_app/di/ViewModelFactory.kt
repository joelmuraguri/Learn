package com.auth_app.authentication_app.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.auth_app.AuthApp
import com.auth_app.authentication_app.ui.screens.home.HomeViewModel
import com.auth_app.authentication_app.ui.screens.login.LoginViewModel
import com.auth_app.authentication_app.ui.screens.signup.SignUpViewModel

object ViewModelFactory {

    val Factory = viewModelFactory {
        initializer {
            LoginViewModel(
                accountService = authAppApplication().appDataContainer.accountService
            )

        }
        initializer {
            SignUpViewModel(
                accountService = authAppApplication().appDataContainer.accountService
            )
        }
        initializer {
            HomeViewModel(
                accountService = authAppApplication().appDataContainer.accountService
            )
        }
    }

}

fun CreationExtras.authAppApplication() : AuthApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AuthApp)