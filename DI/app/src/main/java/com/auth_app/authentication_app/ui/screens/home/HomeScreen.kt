package com.auth_app.authentication_app.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.auth_app.authentication_app.di.ViewModelFactory

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(factory = ViewModelFactory.Factory)
){

    homeViewModel.currentUser?.let {
        UserInfo(
            onLogOut = { homeViewModel.logout() },
            name = it.displayName.toString()
        )
    }
}

@Composable
fun UserInfo(
    onLogOut : () -> Unit,
    name : String
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hi $name",
                modifier = Modifier
                    .padding(32.dp),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 27.sp
            )
            Button(onClick = { onLogOut() }) {
                Text(text = "Log Out")
            }
        }
    }
}