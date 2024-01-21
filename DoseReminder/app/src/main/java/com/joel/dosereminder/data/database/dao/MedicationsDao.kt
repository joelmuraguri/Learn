package com.joel.dosereminder.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.joel.dosereminder.data.database.model.MedicationsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicationsDao {

    @Query("SELECT * FROM Medications_Entity ORDER BY timeStamp DESC")
    fun fetchAllMedication() : Flow<List<MedicationsEntity>>

    @Query("SELECT * FROM Medications_Entity WHERE medicationId=:id")
    fun getMedicationsById(id : Int) : Flow<MedicationsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedication(medicationsEntity: MedicationsEntity)

    @Update
    suspend fun updateMedicationInfo(medicationsEntity: MedicationsEntity)

    @Delete
    suspend fun deleteMedicationInfo(medicationsEntity: MedicationsEntity)

    @Query("DELETE FROM MEDICATIONS_ENTITY")
    suspend fun deleteAllMedicationInfo()

}