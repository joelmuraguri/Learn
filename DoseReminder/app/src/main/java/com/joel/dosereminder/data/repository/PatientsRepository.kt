package com.joel.dosereminder.data.repository

import com.joel.dosereminder.data.database.model.PatientEntity
import com.joel.dosereminder.data.database.relations.PatientWithMedications
import kotlinx.coroutines.flow.Flow

interface PatientsRepository {

    fun fetchAllPatientsInfo() : Flow<List<PatientEntity>>

    fun fetchAllPatientsWithMedication(patientId : Int) : Flow<PatientWithMedications>

    suspend fun insertPatientInfo(patientEntity: PatientEntity)

    suspend fun updatePatientInfo(patientEntity: PatientEntity)

    suspend fun deletePatientInfo(patientEntity: PatientEntity)

    suspend fun deleteAllPatients()
}