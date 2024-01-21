package com.joel.dosereminder.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.joel.dosereminder.di.AppViewModelProvider
import com.joel.dosereminder.presentation.home.EditPatientInfoScreen
import com.joel.dosereminder.presentation.home.HomeEvents
import com.joel.dosereminder.presentation.home.HomeScreen
import com.joel.dosereminder.presentation.home.PatientDetailsScreen
import com.joel.dosereminder.presentation.home.PatientSharedViewModel
import com.joel.dosereminder.presentation.medications.EditMedicationsInfo
import com.joel.dosereminder.utils.PATIENT_ARGUMENT_KEY
import com.joel.dosereminder.utils.Screens

@Composable
fun DoseReminderNavHost(
    patientSharedViewModel: PatientSharedViewModel = viewModel(factory = AppViewModelProvider.factory),
    navHostController: NavHostController
){


    NavHost(
        navController = navHostController,
        startDestination = Screens.Home.route
    ){
        composable(Screens.Home.route){
            HomeScreen(
                patientSharedViewModel = patientSharedViewModel,
                onNavigate = { navigation ->
                    navHostController.navigate(navigation.route)
                },
            )
        }
        composable(
            Screens.EditPatientInfo.route + "?patientId={${PATIENT_ARGUMENT_KEY}}",
            arguments = listOf(navArgument(PATIENT_ARGUMENT_KEY){
                type = NavType.IntType
                defaultValue = -1
            })
        ){ navBackStackEntry ->

            val patientId = navBackStackEntry.arguments!!.getInt(PATIENT_ARGUMENT_KEY)
            LaunchedEffect(key1 = patientId){
                patientSharedViewModel.onEvents(HomeEvents.GetSelectedPatientInfo(patientId))
            }

            val selectedPatient by patientSharedViewModel.selectedPatient.collectAsState()
            LaunchedEffect(key1 = selectedPatient){
                if (selectedPatient != null || patientId == -1){
                    patientSharedViewModel.onEvents(HomeEvents.OnUpdatePatientsTextFields(patientEntity = selectedPatient))
                }
            }
            EditPatientInfoScreen(
                viewModel = patientSharedViewModel,
                onNavigate = { navigation ->
                    navHostController.navigate(navigation.route)
                },
                popBackStack = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(
            Screens.Home.route + "?patientId={${PATIENT_ARGUMENT_KEY}}",
            arguments = listOf(navArgument(PATIENT_ARGUMENT_KEY){
                type = NavType.IntType
                defaultValue = -1
            })
        ){navBackStackEntry ->

            val patientId = navBackStackEntry.arguments!!.getInt(PATIENT_ARGUMENT_KEY)
            LaunchedEffect(key1 = patientId){
                patientSharedViewModel.onEvents(HomeEvents.OnViewPatientDetailsInfo(patientId))
            }

            val selectedPatientWithMedications by patientSharedViewModel.selectedPatientWithMedication.collectAsState()
            LaunchedEffect(key1 = selectedPatientWithMedications){
                if (selectedPatientWithMedications != null || patientId == -1){
                    patientSharedViewModel.onEvents(HomeEvents.UpdatePatientDetailsInfo(patientWithMedications = selectedPatientWithMedications))
                }
            }
            PatientDetailsScreen(
                viewModel = patientSharedViewModel,
                onNavigate = { navigation ->
                    navHostController.navigate(navigation.route)
                },
                popBackStack = {
                    navHostController.popBackStack()
                },
            )
        }
        composable(Screens.EditMedicationInfo.route){
            EditMedicationsInfo()
        }
    }
}