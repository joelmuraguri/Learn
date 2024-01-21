package com.joel.dosereminder.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.joel.dosereminder.data.database.model.MedicationsEntity
import com.joel.dosereminder.data.database.model.PatientEntity
import com.joel.dosereminder.utils.AppEvents

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientDetailsScreen(
    viewModel: PatientSharedViewModel,
    onNavigate : (AppEvents.Navigation) -> Unit,
    popBackStack : () -> Unit,
){

    LaunchedEffect(key1 = true){
        viewModel.appEvents.collect{ appEvents ->
            when(appEvents){
                is AppEvents.Navigation -> {
                    onNavigate(appEvents)
                }
                AppEvents.PopBackStack -> {
                    popBackStack()
                }
            }
        }
    }

    val patientState = viewModel.patientItemState.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    Text(text = "Add Medication")
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {

            PatientInfo(
                firstName = patientState.firstName,
                lastName = patientState.lastName,
                medicationsEntity = patientState.medications,
                onItemClick = {

                }
            )
        }
    }

}

@Composable
fun PatientInfo(
    firstName : String,
    lastName : String,
    medicationsEntity : List<MedicationsEntity>,
    onItemClick :(MedicationsEntity) -> Unit
){

    Column {
        Text(
            text = firstName,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = lastName,
            fontWeight = FontWeight.ExtraBold
        )

        LazyColumn{
            items(medicationsEntity){
                MedicationItem(
                    medicationsEntity = it,
                    onItemClick = { medicationsEntity ->
                        onItemClick(medicationsEntity)
                    }
                )
            }
        }
    }
}

@Composable
fun MedicationItem(
    medicationsEntity: MedicationsEntity,
    onItemClick :(MedicationsEntity) -> Unit
){

    Card(
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .clickable { onItemClick(medicationsEntity) }
    ) {
        Column {
            Text(
                text = medicationsEntity.medicationName,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = medicationsEntity.reminder.toString(),
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}