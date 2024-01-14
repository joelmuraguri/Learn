package com.joel.dosereminder.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Patient_Entity")
data class PatientEntity(
    @PrimaryKey(autoGenerate = true)
    val patientId : Int,
    val userName : String,
    val timeStamp : Long
)