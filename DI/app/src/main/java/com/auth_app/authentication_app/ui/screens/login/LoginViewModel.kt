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

    val uiState = mutableStateOf(LoginUiState())

    private val _email : String
        get() = uiState.value.email

    private val _password : String
        get() = uiState.value.password


    fun onEvents(events: LoginEvents){
        when(events){
            LoginEvents.Login -> {
                viewModelScope.launch {
                    _loginFlow.value = AuthResult.Loading
                    val result = accountService.login(
                        email = _email, password = _password
                    )
                    _loginFlow.value = result
                }
            }
            is LoginEvents.OnEmailChange -> {
                uiState.value = uiState.value.copy(
                    email = _email
                )
            }
            is LoginEvents.OnPasswordChange ->{
                uiState.value = uiState.value.copy(
                    password = _password
                )
            }
        }
    }
}

sealed class LoginEvents{
    data class OnEmailChange(val email : String) : LoginEvents()
    data class OnPasswordChange(val email : String) : LoginEvents()
    data object Login : LoginEvents()
}

data class LoginUiState(
    val email : String = "",
    val password : String = ""
)