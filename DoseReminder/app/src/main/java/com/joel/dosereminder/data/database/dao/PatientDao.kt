package com.joel.dosereminder.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.joel.dosereminder.data.database.model.MedicationsEntity
import com.joel.dosereminder.data.database.model.PatientEntity
import com.joel.dosereminder.data.database.relations.PatientWithMedications
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {

    @Query("SELECT * FROM Patient_Entity ORDER BY timeStamp DESC")
    fun getAllPatients() : Flow<List<PatientEntity>>

    @Query("SELECT * FROM Patient_Entity WHERE patientId=:id")
    fun getPatientById(id : Int) : Flow<PatientEntity>

    @Transaction
    @Query("SELECT * FROM Patient_Entity WHERE patientId=:patientId")
    fun getPatientWithAllMedications(patientId : Int) : Flow<PatientWithMedications>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatient(patientEntity: PatientEntity)

    @Update
    suspend fun updatePatientInfo(patientEntity: PatientEntity)

    @Delete
    suspend fun deletePatientInfo(patientEntity: PatientEntity)

    @Query("DELETE FROM Patient_Entity")
    suspend fun deleteAllPatients()
}