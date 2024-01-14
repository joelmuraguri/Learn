package com.joel.dosereminder.data.repository.impl

import com.joel.dosereminder.data.database.dao.MedicationsDao
import com.joel.dosereminder.data.database.model.MedicationsEntity
import com.joel.dosereminder.data.repository.MedicationsRepository
import kotlinx.coroutines.flow.Flow

class MedicationsRepositoryImpl(
    private val medicationsDao: MedicationsDao
) : MedicationsRepository  {
    override fun fetchAllMedications(): Flow<List<MedicationsEntity>> {
        return medicationsDao.fetchAllMedication()
    }

    override suspend fun insertMedicationsInfo(medicationsEntity: MedicationsEntity) {
        medicationsDao.insertMedication(medicationsEntity)
    }

    override suspend fun updateMedicationsInfo(medicationsEntity: MedicationsEntity) {
        medicationsDao.updateMedicationInfo(medicationsEntity)
    }

    override suspend fun deleteMedicationsInfo(medicationsEntity: MedicationsEntity) {
        medicationsDao.deleteMedicationInfo(medicationsEntity)
    }

    override suspend fun deleteAllMedications() {
        medicationsDao.deleteAllMedicationInfo()
    }

}