package com.joel.dosereminder.data.repository.impl

import com.joel.dosereminder.data.database.dao.PatientDao
import com.joel.dosereminder.data.database.model.PatientEntity
import com.joel.dosereminder.data.database.relations.PatientWithMedications
import com.joel.dosereminder.data.repository.PatientsRepository
import kotlinx.coroutines.flow.Flow

class PatientsRepositoryImpl(
    private val patientDao: PatientDao
) :PatientsRepository {
    override fun fetchAllPatientsInfo(): Flow<List<PatientEntity>> {
        return patientDao.getAllPatients()
    }

    override fun fetchPatientById(patientId: Int): Flow<PatientEntity> {
        return patientDao.getPatientById(patientId)
    }

    override fun fetchAllPatientsWithMedication(patientId: Int): Flow<PatientWithMedications> {
        return patientDao.getPatientWithAllMedications(patientId)
    }

    override suspend fun insertPatientInfo(patientEntity: PatientEntity) {
        patientDao.insertPatient(patientEntity)
    }

    override suspend fun updatePatientInfo(patientEntity: PatientEntity) {
        patientDao.updatePatientInfo(patientEntity)
    }

    override suspend fun deletePatientInfo(patientEntity: PatientEntity) {
        patientDao.deletePatientInfo(patientEntity)
    }

    override suspend fun deleteAllPatients() {
        patientDao.deleteAllPatients()
    }


}