package com.auth_app.authentication_app.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LogInScreen(
    onNavToSignUp : () -> Unit,
    onNavToHome : () -> Unit,
){

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginInputFields(
                onNavToSignUp, onNavToHome
            )
        }

    }
}

@Composable
fun LoginInputFields(
    onNavToSignUp : () -> Unit,
    onNavToHome : () -> Unit,
){

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

//    val authResource = viewModel.loginFlow.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(12.dp)
    ) {

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = "Email")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "")
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Password")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "")
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        )

        Box(
            modifier = Modifier
                .padding(22.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(22.dp)
            ) {
                Button(
                    onClick = {
                        onNavToHome()
                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth(2f)
                ) {
                    Text(text = "Login")
                }

                TextButton(
                    onClick = {
                        onNavToSignUp()
                    },
                ) {
                    Column(
                        modifier = Modifier.padding(6.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Don't have an account?")
                        Text(text = "Click here to sign up")
                    }
                }

            }

//            authResource.value?.let {
//                when(it){
//                    is Resource.Failure -> {
//                        val context = LocalContext.current
//                        Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
//                    }
//                    Resource.Loading -> {
//                        CircularProgressIndicator(
//                            modifier = Modifier
//                                .scale(0.5f)
//                        )
//                    }
//                    is Resource.Success -> {
//                        LaunchedEffect(Unit){
//                            navigator.navigate(ListScreenDestination){
//                                popUpTo(ListScreenDestination) {
//                                    inclusive = true
//                                }
//                            }
//                        }
//                    }
//                }
//            }
        }
    }
}
