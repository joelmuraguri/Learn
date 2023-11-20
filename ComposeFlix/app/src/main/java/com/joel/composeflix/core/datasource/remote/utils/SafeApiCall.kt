package com.joel.composeflix.core.datasource.remote.utils

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import timber.log.Timber
import java.net.ConnectException
import java.net.UnknownHostException

suspend fun <T> safeApiCall(
    block: suspend () -> T
): NetworkResult<T> {
    return try {
        val data = block()
        NetworkResult.Success(data)
    } catch (e: Exception) {
        handleException(e)
    }
}

private fun handleException(e : Exception) : NetworkResult.Error{
    val message = when(e){
        is ServerResponseException -> e.message
        is ClientRequestException -> e.message
        is RedirectResponseException ->  e.message
        is UnknownHostException -> e.message ?: "Not connected to the internet"
        is ConnectException -> "Connection error: ${e.message ?: "Unknown"}"
        else -> "Something went wrong"
    }

    Timber.e(message)
    return NetworkResult.Error(message)
}

sealed class NetworkResult<out T>{
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(val message : String) : NetworkResult<Nothing>()
}
