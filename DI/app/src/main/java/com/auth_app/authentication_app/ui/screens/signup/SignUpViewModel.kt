package com.auth_app.authentication_app.ui.screens.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.auth_app.authentication_app.data.AccountService
import com.auth_app.authentication_app.data.utils.AuthResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val accountService: AccountService
) : ViewModel() {

    private val _signUpFlow = MutableStateFlow<AuthResult<FirebaseUser>?>(null)
    val signUpFlow : StateFlow<AuthResult<FirebaseUser>?> = _signUpFlow

    val uiState = mutableStateOf(SignUpUiState())

    private val _name : String
        get() = uiState.value.email

    private val _email : String
        get() = uiState.value.email

    private val _password : String
        get() = uiState.value.password


    fun onEvents(events: SignUpEvents){
        when(events){
            SignUpEvents.Login -> {
                viewModelScope.launch {
                    _signUpFlow.value = AuthResult.Loading
                    val result = accountService.signup(
                        email = _email,
                        password = _password,
                        name = _name
                    )
                    _signUpFlow.value = result
                }
            }
            is SignUpEvents.OnEmailChange -> {
                uiState.value = uiState.value.copy(
                    email = _email
                )
            }
            is SignUpEvents.OnPasswordChange ->{
                uiState.value = uiState.value.copy(
                    password = _password
                )
            }

            is SignUpEvents.OnNameChange -> {
                uiState.value = uiState.value.copy(
                    name = _name
                )
            }
        }
    }

}

sealed class SignUpEvents{
    data class OnEmailChange(val email : String) : SignUpEvents()
    data class OnPasswordChange(val email : String) : SignUpEvents()
    data class OnNameChange(val name : String) : SignUpEvents()
    data object Login : SignUpEvents()
}

data class SignUpUiState(
    val email : String = "",
    val password : String = "",
    val name : String = ""
)