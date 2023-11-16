package com.joel.jetquiz.presentation.start_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.joel.jetquiz.R
import com.joel.jetquiz.presentation.composables.IconCard
import com.joel.jetquiz.presentation.composables.ReusableButtonCard
import com.joel.jetquiz.ui.theme.JetQuizTheme
import com.joel.jetquiz.ui.theme.wht1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartPage(
    onStartQuiz : () -> Unit
){

    LaunchedEffect(key1 = true){
        // handle navigation to quiz and scores screen
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                actions = {
                    IconCard(
                        icon = R.drawable.round_leaderboard_24,
                        onClick = {

                        }
                    ) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = wht1
                ),
                modifier = Modifier
                        .padding(top = 14.dp)
                ) },
            containerColor = wht1
        ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            StartPageContents(
                onStartQuiz = {
                    onStartQuiz()
                }
            )
        }
    }
}

@Composable
fun StartPageContents(
    onStartQuiz : () -> Unit
){

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.app_logo_animation))

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
        ) {
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .size(270.dp)
            )
        }
        ReusableButtonCard(
            title = "Start Quiz",
            onClick = onStartQuiz,
            modifier = Modifier
                .padding(bottom = 35.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StartPagePreview() {
    JetQuizTheme {
        StartPage(onStartQuiz = {})
    }
}