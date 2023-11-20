package com.joel.composeflix.core.datasource.remote.utils

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import timber.log.Timber

class HttpClientFactory {

    fun createEngine(engine : HttpClientEngine) = HttpClient(engine){
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }


        install(Logging){
            level = LogLevel.BODY
            logger = object : Logger{
                override fun log(message: String) {
                    Timber.i(message)
                }
            }
        }

    }
}

