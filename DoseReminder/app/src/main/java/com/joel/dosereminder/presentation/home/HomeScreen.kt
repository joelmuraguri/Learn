package com.joel.dosereminder.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joel.dosereminder.data.database.model.PatientEntity
import com.joel.dosereminder.utils.AppEvents

@Composable
fun HomeScreen(
    patientSharedViewModel: PatientSharedViewModel,
    onNavigate :(AppEvents.Navigation) -> Unit,
){

    val state = patientSharedViewModel.homeUiState.collectAsState()

    LaunchedEffect(key1 = true){
        patientSharedViewModel.onEvents(HomeEvents.RefreshList)
        patientSharedViewModel.appEvents.collect{ appEvents ->
            when(appEvents){
                is AppEvents.Navigation -> {
                    onNavigate(appEvents)
                }
                else -> {}
            }
        }

    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    patientSharedViewModel.onEvents(HomeEvents.NavToPatientInfo)
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    Text(text = "Add Patient")
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            HomeScreenContent(
                patients = state.value.patients,
                onItemClick = {

                }
            )
        }
    }

}

@Composable
fun HomeScreenContent(
    patients : List<PatientEntity>,
    onItemClick : (PatientEntity) -> Unit
){

    LazyColumn{
        items(patients){ patient ->
            PatientItemCard(
                patientEntity = patient,
                onItemClick = {
                    onItemClick(patient)
                }
            )
        }
    }

}

@Composable
fun PatientItemCard(
    patientEntity: PatientEntity,
    onItemClick : (PatientEntity) -> Unit
){

    Card(
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp,
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .clickable {
                onItemClick(patientEntity)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = patientEntity.firstName)
            Text(text = patientEntity.lastName)
        }
    }
}