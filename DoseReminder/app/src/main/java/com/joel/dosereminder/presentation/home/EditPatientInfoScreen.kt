package com.joel.dosereminder.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joel.dosereminder.utils.AppEvents
import kotlinx.coroutines.flow.collect

@Composable
fun EditPatientInfoScreen(
    viewModel: PatientSharedViewModel,
    onNavigate : (AppEvents.Navigation) -> Unit,
    popBackStack : () -> Unit
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


    val editUiState = viewModel.patientItemState.value

    EditPatientContents(
        firstName = editUiState.firstName,
        onFirstNameChange = {
            viewModel.onEvents(HomeEvents.OnPatientFirstNameChange(it))
        },
        lastName = editUiState.lastName,
        onLastNameChange = {
             viewModel.onEvents(HomeEvents.OnPatientLastNameChange(it))
        },
        onCancel = {
             viewModel.onEvents(HomeEvents.PopBackStack)
        },
        onSave = {
            viewModel.onEvents(HomeEvents.OnAddPatient)
        }
    )
}

@Composable
fun EditPatientContents(
    firstName : String,
    onFirstNameChange : (String) -> Unit,
    lastName : String,
    onLastNameChange : (String) -> Unit,
    onCancel : () -> Unit,
    onSave : () -> Unit,
){

    Column(
        modifier = Modifier
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        EditTextField(
            value = firstName,
            onValueChange = { onFirstNameChange(it) },
            label = "First Name"
        )
        EditTextField(
            value = lastName,
            onValueChange = { onLastNameChange(it) },
            label = "Last Name"
        )


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            ActionButtons(
                text = "Cancel",
                onAction = {
                    onCancel()
                }
            )
            ActionButtons(
                text = "Save",
                onAction = {
                   onSave()
                }
            )
        }
    }
}

@Composable
fun EditTextField(
    value : String,
    onValueChange : (String) -> Unit,
    label : String
){

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        maxLines = 1,
        label = {
            Text(text = label)
        },
        modifier = Modifier
            .padding(
                vertical = 10.dp
            )
            .fillMaxWidth()
    )
}

@Composable
fun ActionButtons(
    text : String,
    onAction : () -> Unit
){

    Button(onClick = { onAction() }) {
        Text(text = text)
    }

}