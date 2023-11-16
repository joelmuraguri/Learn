package com.joel.jetquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joel.jetquiz.presentation.quiz.Quiz
import com.joel.jetquiz.presentation.start_page.StartPage
import com.joel.jetquiz.ui.theme.JetQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetQuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        JetQuizApp()
                    }
                }
            }
        }
    }
}

@Composable
fun JetQuizApp(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "start_page_route"){
        composable("start_page_route"){
            StartPage(
                onStartQuiz = {
                    navController.navigate("quiz_screen_route")
                },

            )
        }
        composable("quiz_screen_route"){
            Quiz(
                onNavigate = { jetQuizEvents ->
                    navController.navigate(jetQuizEvents.route)
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetQuizTheme {

    }
}