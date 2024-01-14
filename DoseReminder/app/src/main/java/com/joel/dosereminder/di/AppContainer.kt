package com.joel.dosereminder.di

import android.content.Context
import com.joel.dosereminder.data.database.DoseReminderDatabase
import com.joel.dosereminder.data.repository.MedicationsRepository
import com.joel.dosereminder.data.repository.PatientsRepository
import com.joel.dosereminder.data.repository.impl.MedicationsRepositoryImpl
import com.joel.dosereminder.data.repository.impl.PatientsRepositoryImpl

interface AppDataContainer{
    val patientsRepository : PatientsRepository
    val medicationsRepository : MedicationsRepository
}

class AppContainer(
    private val context: Context
) : AppDataContainer {

    override val patientsRepository: PatientsRepository by lazy {
        PatientsRepositoryImpl(DoseReminderDatabase.getDatabaseInstance(context).patientsDao())
    }

    override val medicationsRepository: MedicationsRepository by lazy {
       MedicationsRepositoryImpl(DoseReminderDatabase.getDatabaseInstance(context).medicationsDao())
    }
}
