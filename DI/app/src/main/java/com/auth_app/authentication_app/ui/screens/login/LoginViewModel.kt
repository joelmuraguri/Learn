package com.auth_app.authentication_app.ui.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.auth_app.authentication_app.data.AccountService
import com.auth_app.authentication_app.data.utils.AuthResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val accountService: AccountService
) : ViewModel() {

    private val _loginFlow = MutableStateFlow<AuthResult<FirebaseUser>?>(null)
    val loginFlow : StateFlow<AuthResult<FirebaseUser>?> = _loginFlow


    private var _email = mutableStateOf("")
    val email : String
        get() = _email.value

    private var _password = mutableStateOf("")
    val password : String
        get() = _password.value

    init {
        if (accountService.user != null){
            _loginFlow.value = AuthResult.Success(accountService.user!!)
        }
    }

    fun onEvents(events: LoginEvents){
        when(events){
            LoginEvents.Login -> {
                viewModelScope.launch {
                    _loginFlow.value = AuthResult.Loading
                    val result = accountService.login(
                        email = _email.value, password = _password.value
                    )
                    _loginFlow.value = result
                }
            }
            is LoginEvents.OnEmailChange -> {
                _email.value = events.email
            }
            is LoginEvents.OnPasswordChange ->{
                _password.value = events.password
            }
        }
    }
}

sealed class LoginEvents{
    data class OnEmailChange(val email : String) : LoginEvents()
    data class OnPasswordChange(val password : String) : LoginEvents()
    data object Login : LoginEvents()
}