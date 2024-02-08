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


    private var _name = mutableStateOf("")
    val name : String
        get() = _name.value

    private var _email = mutableStateOf("")
    val email : String
        get() = _email.value

    private var _password = mutableStateOf("")
    val password : String
        get() = _password.value


    fun onEvents(events: SignUpEvents){
        when(events){
            SignUpEvents.SignUp -> {
                viewModelScope.launch {
                    _signUpFlow.value = AuthResult.Loading
                    val result = accountService.signup(
                        email = _email.value,
                        password = _password.value,
                        name = _name.value
                    )
                    _signUpFlow.value = result
                }
            }
            is SignUpEvents.OnEmailChange -> {
                _email.value = events.email
            }
            is SignUpEvents.OnPasswordChange ->{
                _password.value = events.password
            }

            is SignUpEvents.OnNameChange -> {
                _name.value = events.name
            }
        }
    }

}

sealed class SignUpEvents{
    data class OnEmailChange(val email : String) : SignUpEvents()
    data class OnPasswordChange(val password : String) : SignUpEvents()
    data class OnNameChange(val name : String) : SignUpEvents()
    data object SignUp : SignUpEvents()
}
