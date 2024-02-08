package com.auth_app.authentication_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.auth_app.authentication_app.ui.screens.HomeScreen
import com.auth_app.authentication_app.ui.screens.login.LogInScreen
import com.auth_app.authentication_app.ui.screens.signup.SignUpScreen
import com.auth_app.authentication_app.ui.theme.AuthenticationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthenticationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   AuthApp()
                }
            }
        }
    }
}

@Composable
fun AuthApp(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route){
            LogInScreen(
                onNavToSignUp = { navController.navigate(Screen.SignUp.route) },
                onNavToHome = { navController.navigate(Screen.Home.route) }
            )
        }
        composable(Screen.SignUp.route){
            SignUpScreen(
                onNavToLogin = { navController.navigate(Screen.Login.route) },
                onNavToHome = { navController.navigate(Screen.Home.route) }
            )
        }
        composable(Screen.Home.route){
            HomeScreen(
              onLogOut = {
                  navController.navigate(Screen.Login.route)
              }
            )
        }
    }
}

sealed class Screen(val route : String){
    data object Login : Screen("login")
    data object SignUp : Screen("signup")
    data object Home : Screen("home")
}