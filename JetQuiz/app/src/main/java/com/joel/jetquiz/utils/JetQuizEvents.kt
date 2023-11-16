package com.joel.jetquiz.utils

sealed class JetQuizEvents{

    data class Navigate(val route : String) : JetQuizEvents()
}
