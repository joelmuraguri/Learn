package com.auth_app.authentication_app.data.utils

sealed class AuthResult<out R> {

    data class Success<out R>(val result: R) : AuthResult<R>()
    data class Failure(val exception: Exception) : AuthResult<Nothing>()
    object Loading : AuthResult<Nothing>()
}