package com.joel.dosereminder.data.repository

import com.joel.dosereminder.data.database.model.MedicationsEntity
import kotlinx.coroutines.flow.Flow

interface MedicationsRepository {

    fun fetchAllMedications() : Flow<List<MedicationsEntity>>

    suspend fun insertMedicationsInfo(medicationsEntity: MedicationsEntity)

    suspend fun updateMedicationsInfo(medicationsEntity: MedicationsEntity)

    suspend fun deleteMedicationsInfo(medicationsEntity: MedicationsEntity)

    suspend fun deleteAllMedications()

}