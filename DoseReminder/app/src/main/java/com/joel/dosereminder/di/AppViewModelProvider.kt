package com.joel.dosereminder.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.joel.dosereminder.DoseReminderApp
import com.joel.dosereminder.presentation.home.PatientSharedViewModel

object AppViewModelProvider {

    val factory = viewModelFactory {

        initializer {
            PatientSharedViewModel(
                inventoryApplication().container.patientsRepository
            )
        }

    }
}

fun CreationExtras.inventoryApplication(): DoseReminderApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DoseReminderApp)