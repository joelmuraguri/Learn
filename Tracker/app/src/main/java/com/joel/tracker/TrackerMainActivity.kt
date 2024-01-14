package com.joel.tracker

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import com.joel.tracker.ui.theme.TrackerTheme

class TrackerMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            0
        )
        setContent {
            TrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Button(onClick = {
                                Log.d("START", "CLICKED")
                                Intent(applicationContext, LocationService::class.java).apply {
                                    action = LocationService.ACTION_START
                                    startService(this)
                                    Log.d("START TRACKING", "TRACKING LOCATION..")

                                }
                            }) {
                                Text(text = "Start Tracking")
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = {
                                Log.d("STOP", "CLICKED")
                                Intent(applicationContext, LocationService::class.java).apply {
                                    action = LocationService.ACTION_STOP
                                    startService(this)
                                    Log.d("STOP TRACKING", "STOP TRACKING LOCATION..")
                                }
                            }) {
                                Text(text = "Stop Tracking")
                            }
                        }



                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(text = "Connect")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Tracker(
    viewModel: TrackerViewModel,
    onStartService : () -> Unit,
    onStopService : () -> Unit
){

    val state = viewModel.state

    Scaffold(

    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Switch(
                checked = state.value.isTrackerEnabled,
                onCheckedChange = {
                    viewModel.onEvents(TrackerUiEvents.OnTrackerEnabled(it))
                },
                thumbContent = {
                    if (state.value.isTrackerEnabled){
                        Icon(imageVector = Icons.Default.Check, contentDescription = "")
                        onStartService()
                    } else {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "")
                        onStopService()
                    }
                },
                modifier = Modifier
                    .width(150.dp)
            )
        }
    }
}

class TrackerViewModel : ViewModel(){

    private val _state = mutableStateOf(TrackerState())
    val state : State<TrackerState> = _state

    fun onEvents(events: TrackerUiEvents){
        when(events){
            is TrackerUiEvents.OnTrackerEnabled -> {
                _state.value = _state.value.copy(isTrackerEnabled = events.isTrackerEnabled)
            }
        }
    }
}


sealed class TrackerUiEvents{
    data class OnTrackerEnabled(val isTrackerEnabled: Boolean) : TrackerUiEvents()
}
data class TrackerState(
    val isTrackerEnabled : Boolean = false
)