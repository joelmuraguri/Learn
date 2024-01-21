package com.joel.dosereminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.joel.dosereminder.navigation.DoseReminderNavHost
import com.joel.dosereminder.presentation.home.PatientSharedViewModel
import com.joel.dosereminder.ui.theme.DoseReminderTheme

class DoseReminderMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoseReminderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navHostController = rememberNavController()
                    val patientSharedViewModel = viewModels<PatientSharedViewModel>()
                    DoseReminderNavHost(
                        navHostController = navHostController
                    )
                }
            }
        }
    }
}


