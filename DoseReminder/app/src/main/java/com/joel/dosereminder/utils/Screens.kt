package com.joel.dosereminder.utils

const val PATIENT_ARGUMENT_KEY = "patientId"
const val MEDICATION_ARGUMENT_KEY = "medicationId"

sealed class Screens(val route : String) {

    data object Home : Screens("home_route")
    data object EditPatientInfo : Screens("edit_patient_info_route")
    data object PatientDetails : Screens("patient_details_route")
    data object EditMedicationInfo : Screens("edit_dose_info_route")

}