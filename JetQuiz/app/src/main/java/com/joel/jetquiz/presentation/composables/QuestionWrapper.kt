package com.joel.jetquiz.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joel.jetquiz.R
import com.joel.jetquiz.data.QuizModel
import com.joel.jetquiz.ui.theme.JetQuizTheme



@Composable
fun QuestionWrapper(
    quiz: QuizModel,
    selectedAnswer : String?,
    onSelectedAnswer : (String) -> Unit,

){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
    ) {
        
        Box {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "${quiz.timer}",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )

                Text(
                    text = quiz.questionTitle,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        if (quiz.image != null){
            Column {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = quiz.image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                GridStateAnswers(
                    possibleAnswers = quiz.possibleAnswers,
                    onChoiceChange = onSelectedAnswer,
                    selectedAnswer = selectedAnswer
                )
            }
        } else {
            Box {
                LazyStateAnswers(
                    possibleAnswers = quiz.possibleAnswers,
                    onChoiceChange = onSelectedAnswer,
                    selectedAnswer = selectedAnswer
                )
            }
        }
    }
}

@Composable
fun GridStateAnswers(
    possibleAnswers : List<String>,
    selectedAnswer: String?,
    onChoiceChange : (String) -> Unit
){

    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ){
        items(possibleAnswers){answers ->
            val selected = answers == selectedAnswer
            ReusableButtonCard(
                title = answers,
                onClick = {
                    onChoiceChange(answers)
                },
                modifier = Modifier
                    .padding(10.dp),
                selected = selected
            )
        }
    }

}

@Composable
fun LazyStateAnswers(
    possibleAnswers : List<String>,
    selectedAnswer: String?,
    onChoiceChange : (String) -> Unit
){

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        items(possibleAnswers){answers ->
            val selected = answers == selectedAnswer
            ReusableButtonCard(
                title = answers,
                onClick = {
                    onChoiceChange(answers)
                },
                modifier = Modifier
                    .padding(10.dp),
                selected = selected
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionWrapperPreviewWithImage() {
    val quiz = QuizModel(
        timer = 300L,
        questionTitle = "The Mona Lisa was painted by?",
        possibleAnswers = listOf("Michelangelo", "Leonardo Da Vinci", "Leonardo DiCaprio", "Vincent van Gogh"),
        correctAnswer = "Leonardo Da Vinci",
        image = R.drawable.mona_lisa
    )
    JetQuizTheme {
        QuestionWrapper(
            quiz,
            selectedAnswer = null,
            onSelectedAnswer = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionWrapperPreviewWithoutImage() {
    val quiz = QuizModel(
        timer = 300L,
        questionTitle = "What planet is closest to the sun in our galaxy?",
        possibleAnswers = listOf("Mercury","Saturn","Earth","Jupiter"),
        correctAnswer = "Mercury",
        image = null
    )
    JetQuizTheme {
        QuestionWrapper(
            quiz = quiz,
            selectedAnswer = null,
            onSelectedAnswer = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LazyStateAnswersPreview() {
    val possibleAnswers = listOf("Michelangelo", "Leonardo Da Vinci", "Leonardo DiCaprio", "Vincent van Gogh")
    JetQuizTheme {
        LazyStateAnswers(
            possibleAnswers,
            selectedAnswer = null,
            onChoiceChange = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GridStateAnswersPreview() {
    val possibleAnswers = listOf("Michelangelo", "Leonardo Da Vinci", "Leonardo DiCaprio", "Vincent van Gogh")
    JetQuizTheme {
        GridStateAnswers(
            possibleAnswers,
            selectedAnswer = null,
            onChoiceChange = {}
        )
    }
}