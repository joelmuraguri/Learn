package com.auth_app.authentication_app.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.auth_app.authentication_app.data.utils.AuthResult
import com.auth_app.authentication_app.data.utils.await

class AuthRepository(
    private val auth : FirebaseAuth
) : AccountService {

    override val user: FirebaseUser?
        get() = auth.currentUser

    override suspend fun login(email: String, password: String): AuthResult<FirebaseUser> {
        return try {
            val result =  auth.signInWithEmailAndPassword(email, password).await()
            AuthResult.Success(result.user!!)
        } catch (e : Exception){
            e.printStackTrace()
            AuthResult.Failure(e)
        }
    }

    override suspend fun signup(name: String, email: String, password: String): AuthResult<FirebaseUser> {
        return try {
            val result =  auth.createUserWithEmailAndPassword(email, password).await()
            result.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
            return AuthResult.Success(result.user!!)
        } catch (e : Exception){
            e.printStackTrace()
            AuthResult.Failure(e)
        }

    }

    override fun logout() {
        auth.signOut()
    }
}