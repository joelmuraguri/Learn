package com.joel.dosereminder.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joel.dosereminder.data.database.dao.MedicationsDao
import com.joel.dosereminder.data.database.dao.PatientDao
import com.joel.dosereminder.data.database.model.MedicationsEntity
import com.joel.dosereminder.data.database.model.PatientEntity

@Database(
    entities = [
        PatientEntity::class,
        MedicationsEntity::class],
    version = 1
)
abstract class DoseReminderDatabase : RoomDatabase() {
    abstract val patientsDao : PatientDao
    abstract val medicationsDao : MedicationsDao
}