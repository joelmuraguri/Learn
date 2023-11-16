package com.joel.jetquiz.data

data class QuizModel(
    val questionTitle : String,
    val correctAnswer : String,
    val possibleAnswers : List<String>,
    val timer : Long,
    val image : Int? = null,
)
