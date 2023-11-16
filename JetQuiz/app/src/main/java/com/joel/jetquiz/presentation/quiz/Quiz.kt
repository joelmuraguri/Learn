package com.joel.jetquiz.presentation.quiz

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.joel.jetquiz.presentation.composables.OutlinedActionButtonCard
import com.joel.jetquiz.presentation.composables.QuestionWrapper
import com.joel.jetquiz.ui.theme.grn3
import com.joel.jetquiz.ui.theme.wht3
import com.joel.jetquiz.utils.JetQuizEvents

@Composable
fun Quiz(
    quizViewModel: QuizViewModel = viewModel(),
    onNavigate : (JetQuizEvents.Navigate) -> Unit
){


    val quizData = quizViewModel.quizData ?: return

    LaunchedEffect(key1 = true){
        quizViewModel.uiEvents.collect{ jetQuizEvents ->
            when(jetQuizEvents){
                is JetQuizEvents.Navigate -> {
                    onNavigate(jetQuizEvents)
                }
            }
        }
    }


    QuizContent(
        content = {paddingValues ->
            AnimatedContent(
                targetState = quizData,
                transitionSpec = {
                    val animationSpec: TweenSpec<IntOffset> =
                        tween(300)
                    val direction = getTransitionDirection(
                        initialIndex = initialState.questionIndex,
                        targetIndex = targetState.questionIndex,
                    )
                    slideIntoContainer(
                        towards = direction,
                        animationSpec = animationSpec,
                    ) togetherWith slideOutOfContainer(
                        towards = direction,
                        animationSpec = animationSpec
                    )
                }, label = ""
            ) { targetState ->
                if (targetState.quizes.isNotEmpty()){
                    Box(
                        modifier = Modifier
                            .padding(paddingValues)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Box(
                                contentAlignment = Alignment.TopCenter,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 25.dp)
                            ) {
                                Text(
                                    text = "Question ${targetState.questionIndex + 1} / ${targetState.totalQuestions}",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            QuestionWrapper(
                                quiz = quizData.quizes[targetState.questionIndex],
                                onSelectedAnswer = { answer ->
                                    quizViewModel.onEvents(QuizEvents.OnChoiceChange(answer))
                                },
                                selectedAnswer = quizViewModel.selectedAnswer
                            )
                        }
                    }
                } else {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxSize()
                    ){
                        Text(
                            text = "QUIZES SHOULD BE HERE, AN UNEXPECTED ERROR OCCURRED",
                            fontSize = 24.sp
                        )
                    }
                }
            }
        },
        isNextEnabled = quizViewModel.isNextEnabled,
        onPreviousPressed = {
             quizViewModel.onEvents(QuizEvents.ClickPrevious)
        },
        onSubmitPressed = {
            quizViewModel.onEvents(QuizEvents.Submit)
        },
        onNextPressed = {
           quizViewModel.onEvents(QuizEvents.ClickNext)
        },
        quizData = quizData
    )

}


@Composable
fun QuizContent(
    content: @Composable (PaddingValues) -> Unit,
    isNextEnabled : Boolean,
    onPreviousPressed : () -> Unit,
    onSubmitPressed : () -> Unit,
    onNextPressed : () -> Unit,
    quizData: QuizData
){



    Scaffold(
        bottomBar = {
            QuizBottomBar(
                showPreviousButton = quizData.showPreviousButton,
                showSubmitButton = quizData.showSubmitButton,
                onPreviousPressed = { onPreviousPressed() },
                onSubmitPressed = { onSubmitPressed() },
                onNextPressed = { onNextPressed() },
                isNextButtonEnabled =  isNextEnabled
            )
        },
        content = content
    )

}

@Composable
fun QuizBottomBar(
    showPreviousButton : Boolean,
    showSubmitButton : Boolean,
    onPreviousPressed : () -> Unit,
    onSubmitPressed : () -> Unit,
    onNextPressed : () -> Unit,
    isNextButtonEnabled : Boolean
){

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shadowElevation = 8.dp
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showPreviousButton){
                OutlinedActionButtonCard(
                    title = "Previous",
                    onClick = { onPreviousPressed() },
                    modifier = Modifier
                        .weight(1f)
                )
            }
            if (showSubmitButton){
                OutlinedActionButtonCard(
                    title = "Submit",
                    onClick = { onSubmitPressed() },
                    modifier = Modifier
                        .weight(1f)
                )

            } else {
                Button(
                    onClick = { onNextPressed() },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    enabled = isNextButtonEnabled,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (!isNextButtonEnabled) Color.Black else grn3
                    )

                ) {
                    Text(
                        text = "Next",
                        color = wht3
                    )
                }
            }
        }
    }
}

private fun getTransitionDirection(
    initialIndex: Int,
    targetIndex: Int
): AnimatedContentTransitionScope.SlideDirection {
    return if (targetIndex > initialIndex) {
        // Going forwards in the survey: Set the initial offset to start
        // at the size of the content so it slides in from right to left, and
        // slides out from the left of the screen to -fullWidth
        AnimatedContentTransitionScope.SlideDirection.Start
    } else {
        // Going back to the previous question in the set, we do the same
        // transition as above, but with different offsets - the inverse of
        // above, negative fullWidth to enter, and fullWidth to exit.
        AnimatedContentTransitionScope.SlideDirection.End
    }
}