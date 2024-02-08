package com.auth_app.authentication_app.ui.screens.signup

import android.widget.Toast
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.auth_app.authentication_app.Screen
import com.auth_app.authentication_app.data.utils.AuthResult
import com.auth_app.authentication_app.di.ViewModelFactory
import com.auth_app.authentication_app.ui.screens.login.LoginEvents

@Composable
fun SignUpScreen(
    onNavToLogin : () -> Unit,
    signUpViewModel: SignUpViewModel = viewModel(factory = ViewModelFactory.Factory),
    navController: NavHostController
){

    val authResource = signUpViewModel.signUpFlow.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            SignUpInputFields(
                onNavToLogin,
                onSignUp = {
                    signUpViewModel.onEvents(SignUpEvents.SignUp)
                },
                email = signUpViewModel.email,
                password = signUpViewModel.password,
                name = signUpViewModel.name,
                onEmailChange = {
                    signUpViewModel.onEvents(SignUpEvents.OnEmailChange(it))
                },
                onPasswordChange = {
                    signUpViewModel.onEvents(SignUpEvents.OnPasswordChange(it))
                },
                onNameChange = {
                    signUpViewModel.onEvents(SignUpEvents.OnNameChange(it))
                }
            )
        }

        authResource.value?.let {
            when(it){
                is AuthResult.Failure -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
                }
                AuthResult.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .scale(0.5f)
                    )
                }
                is AuthResult.Success -> {
                    LaunchedEffect(Unit){
                        navController.navigate(Screen.Home.route)
                    }
                }
            }
        }
    }
}

@Composable
fun SignUpInputFields(
    onNavToLogin : () -> Unit,
    onSignUp : () -> Unit,
    email : String,
    onEmailChange : (String) -> Unit,
    password : String,
    onPasswordChange : (String) -> Unit,
    name : String,
    onNameChange : (String) -> Unit,
){


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(12.dp)
    ) {

        OutlinedTextField(
            value = name,
            onValueChange = {
                onNameChange(it)
            },
            label = {
                Text(text = "Name")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                onEmailChange(it)
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
               onPasswordChange(it)
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
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(2.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                       onSignUp()
                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth(2f)
                ) {
                    Text(text = "SignUp")
                }

                TextButton(
                    onClick = {
                        onNavToLogin()
                    },
                ) {
                    Column(
                        modifier = Modifier.padding(6.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Already have an account?")
                        Text(text = "Click here to login")
                    }
                }

            }

        }

//        authResource.value?.let {
//            when(it){
//                is Resource.Failure -> {
//                    Log.d("SIGN UP", "${it.exception.message}")
//                    val context = LocalContext.current
//                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
//                }
//                Resource.Loading -> {
//                    CircularProgressIndicator(
//                        modifier = Modifier
//                            .scale(0.5f)
//                    )
//                }
//                is Resource.Success -> {
//                    LaunchedEffect(Unit){
//                        navigator.navigate(ListScreenDestination){
//                            popUpTo(ListScreenDestination){
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }
}