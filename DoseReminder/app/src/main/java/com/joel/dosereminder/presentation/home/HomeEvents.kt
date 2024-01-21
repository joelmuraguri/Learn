package com.joel.dosereminder.presentation.home

import com.joel.dosereminder.data.database.model.PatientEntity
import com.joel.dosereminder.data.database.relations.PatientWithMedications

sealed class HomeEvents {
    data object OnAddPatient : HomeEvents()
    data class OnViewPatientDetailsInfo(val patientId: Int) : HomeEvents()
    data class OnDeletePatient(val patientEntity: PatientEntity) : HomeEvents()
    data class OnPatientFirstNameChange(val name : String) : HomeEvents()
    data class OnPatientLastNameChange(val name : String) : HomeEvents()
    data class OnUpdatePatientsTextFields(val patientEntity: PatientEntity?) : HomeEvents()
    data object RefreshList : HomeEvents()
    data object PopBackStack : HomeEvents()
    data class GetSelectedPatientInfo(val patientId: Int) : HomeEvents()
    data class OnPatientItemClick(val patientEntity: PatientEntity) : HomeEvents()
    data class UpdatePatientDetailsInfo(val patientWithMedications: PatientWithMedications?) : HomeEvents()
    data class OnEditPatientInfo(val patientEntity: PatientEntity) : HomeEvents()
    data object NavToPatientInfo : HomeEvents()

}