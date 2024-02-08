package com.auth_app.authentication_app.ui.screens.login

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

@Composable
fun LogInScreen(
    onNavToSignUp : () -> Unit,
    loginViewModel: LoginViewModel = viewModel(factory = ViewModelFactory.Factory),
    navController: NavHostController
){

    val authResource = loginViewModel.loginFlow.collectAsState()


    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginInputFields(
                onNavToSignUp,
                onSignIn = {
                    loginViewModel.onEvents(LoginEvents.Login)
                },
                email = loginViewModel.email,
                password = loginViewModel.password,
                onEmailChange = {
                    loginViewModel.onEvents(LoginEvents.OnEmailChange(it))
                },
                onPasswordChange = {
                    loginViewModel.onEvents(LoginEvents.OnPasswordChange(it))
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
fun LoginInputFields(
    onNavToSignUp : () -> Unit,
    onSignIn : () -> Unit,
    email : String,
    onEmailChange : (String) -> Unit,
    password : String,
    onPasswordChange : (String) -> Unit,
){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(12.dp)
    ) {

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
                        onSignIn()
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

        }
    }
}
