package com.joel.dosereminder.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joel.dosereminder.data.database.model.MedicationsEntity
import com.joel.dosereminder.data.database.model.PatientEntity
import com.joel.dosereminder.data.database.relations.PatientWithMedications
import com.joel.dosereminder.data.repository.PatientsRepository
import com.joel.dosereminder.utils.AppEvents
import com.joel.dosereminder.utils.Screens
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class HomeState(
    val patients : List<PatientEntity> = emptyList(),
    val isLoading : Boolean = false
)

data class PatientItemState(
    var firstName : String = "",
    var lastName : String = "",
    var patientId : Int = 0,
    var medications : List<MedicationsEntity> = emptyList()
)

/**
 *
 *
 *
 * This ViewModel handles all about  Patients
 *
 *
 * */
class PatientSharedViewModel(
    private val patientsRepository: PatientsRepository
) : ViewModel() {

    private val _patientItemState = mutableStateOf(PatientItemState())
    val patientItemState : State<PatientItemState> = _patientItemState

    private val _homeUiState = MutableStateFlow(HomeState(isLoading = true))
    val homeUiState : StateFlow<HomeState> = _homeUiState.asStateFlow()

    private val allPatients = patientsRepository.fetchAllPatientsInfo().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue =  emptyList()
    )

    private val _selectedPatient: MutableStateFlow<PatientEntity?> = MutableStateFlow(null)
    val selectedPatient : StateFlow<PatientEntity?> = _selectedPatient

    private val _selectedPatientWithMedication: MutableStateFlow<PatientWithMedications?> = MutableStateFlow(null)
    val selectedPatientWithMedication : StateFlow<PatientWithMedications?> = _selectedPatientWithMedication

    private val _appEvents = Channel<AppEvents>()
    val appEvents = _appEvents.receiveAsFlow()

    fun onEvents(events: HomeEvents){
        when(events){
            HomeEvents.OnAddPatient -> {
                viewModelScope.launch {
                    val patient = PatientEntity(
                        firstName = _patientItemState.value.firstName,
                        lastName = _patientItemState.value.lastName,
                        timeStamp = System.currentTimeMillis()
                    )
                    patientsRepository.insertPatientInfo(patient)
                    _appEvents.send(AppEvents.Navigation(Screens.Home.route))
                }
            }
            is HomeEvents.OnDeletePatient -> {
                viewModelScope.launch {
                    val patients = PatientEntity(
                        firstName = _patientItemState.value.firstName,
                        lastName = _patientItemState.value.lastName
                    )
                    patientsRepository.deletePatientInfo(patients)
                    _appEvents.send(AppEvents.Navigation(Screens.Home.route))
                }
            }
            is HomeEvents.OnViewPatientDetailsInfo -> {
                viewModelScope.launch {
                    patientsRepository.fetchAllPatientsWithMedication(events.patientId).collect{ patientWithMedications ->
                        _selectedPatientWithMedication.value = patientWithMedications
                    }
                }
            }
            HomeEvents.RefreshList -> {
                viewModelScope.launch {
                    _homeUiState.update { homeUiState ->
                        homeUiState.copy(
                            patients = allPatients.value,
                            isLoading = false
                        )
                    }
                }
            }
            is HomeEvents.OnUpdatePatientsTextFields -> {
                viewModelScope.launch {
                    if (events.patientEntity != null){
                        _patientItemState.value.firstName = events.patientEntity.firstName
                        _patientItemState.value.lastName = events.patientEntity.lastName
                    } else {
                        _patientItemState.value.firstName = ""
                        _patientItemState.value.lastName = ""
                    }
                }
            }
            is HomeEvents.OnPatientFirstNameChange -> {
                _patientItemState.value = _patientItemState.value.copy(
                    firstName = events.name
                )
            }
            is HomeEvents.OnPatientLastNameChange -> {
                _patientItemState.value = _patientItemState.value.copy(
                    lastName = events.name
                )
            }
            HomeEvents.PopBackStack -> {
                viewModelScope.launch {
                    _appEvents.send(AppEvents.PopBackStack)
                }
            }
            is HomeEvents.GetSelectedPatientInfo -> {
                viewModelScope.launch {
                    patientsRepository.fetchPatientById(events.patientId).collect{ patient ->
                        _selectedPatient.value = patient
                    }
                }
            }
            is HomeEvents.OnPatientItemClick -> {
                viewModelScope.launch {
                    _appEvents.send(AppEvents.Navigation(Screens.PatientDetails.route + "?patientId=${events.patientEntity.patientId}"))
                }
            }

            is HomeEvents.UpdatePatientDetailsInfo -> {
                _homeUiState.value = _homeUiState.value.copy(isLoading = true)
                viewModelScope.launch {
                    if (events.patientWithMedications != null){
                        _patientItemState.value.patientId = events.patientWithMedications.patientEntity.patientId
                        _patientItemState.value.firstName = events.patientWithMedications.patientEntity.firstName
                        _patientItemState.value.lastName = events.patientWithMedications.patientEntity.lastName
                        _patientItemState.value.medications = events.patientWithMedications.medications
                    } else {
                        _patientItemState.value.patientId = 0
                        _patientItemState.value.firstName = ""
                        _patientItemState.value.lastName = ""
                        _patientItemState.value.medications = emptyList()
                    }
                }
            }
            is HomeEvents.OnEditPatientInfo -> {
                viewModelScope.launch {
                    _appEvents.send(AppEvents.Navigation(Screens.EditPatientInfo.route + "?patientId=${events.patientEntity.patientId}"))
                }
            }

            HomeEvents.NavToPatientInfo -> {
                viewModelScope.launch {
                    _appEvents.send(AppEvents.Navigation(Screens.EditPatientInfo.route))
                }
            }
        }
    }
}

