package com.joel.dosereminder.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joel.dosereminder.data.database.dao.MedicationsDao
import com.joel.dosereminder.data.database.dao.PatientDao
import com.joel.dosereminder.data.database.model.MedicationsEntity
import com.joel.dosereminder.data.database.model.PatientEntity

@Database(
    entities = [
        PatientEntity::class,
        MedicationsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DoseReminderDatabase : RoomDatabase() {
    abstract fun patientsDao() : PatientDao
    abstract fun medicationsDao() : MedicationsDao



    companion object{
        @Volatile
        //Instantiate our db, It's nullable
        private var INSTANCE: DoseReminderDatabase ?= null

        fun getDatabaseInstance(context: Context) : DoseReminderDatabase{
            // if instance is null, a new database is created
            // if instance is not null, return it
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(context, DoseReminderDatabase::class.java, "DoseReminder_Database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}