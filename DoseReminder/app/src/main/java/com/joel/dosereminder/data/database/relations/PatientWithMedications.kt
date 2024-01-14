package com.joel.dosereminder.data.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.joel.dosereminder.data.database.model.MedicationsEntity
import com.joel.dosereminder.data.database.model.PatientEntity

data class PatientWithMedications(
    @Embedded val patientEntity: PatientEntity,
    @Relation(
        entity = PatientEntity::class,
        parentColumn = "patientId",
        entityColumn = "patientId"
    )
    val medications: List<MedicationsEntity>
)
