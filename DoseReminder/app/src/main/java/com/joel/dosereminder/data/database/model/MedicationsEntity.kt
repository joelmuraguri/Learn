package com.joel.dosereminder.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Medications_Entity")
data class MedicationsEntity(
    @PrimaryKey(autoGenerate = true) val medicationId : Int,
    val medicationName : String,
    @ColumnInfo("patientId")
    val patientId : Int,
    val reminder : Long,
    val timeStamp : Long
)
