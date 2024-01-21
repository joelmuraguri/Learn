package com.joel.dosereminder.utils

sealed class AppEvents {
    data class Navigation(val route : String) : AppEvents()
    data object PopBackStack : AppEvents()
}