package com.auth_app.authentication_app.data

import com.google.firebase.auth.FirebaseUser
import com.auth_app.authentication_app.data.utils.AuthResult

interface AccountService {

    val user : FirebaseUser?

    suspend fun login(
        email : String,
        password : String
    ) : AuthResult<FirebaseUser>

    suspend fun signup(
        name : String,
        email : String,
        password : String
    ) : AuthResult<FirebaseUser>

    fun logout()

}