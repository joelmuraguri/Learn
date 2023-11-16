package com.joel.jetquiz.presentation.quiz

sealed class QuizEvents{

    object ClickNext : QuizEvents()
    object ClickPrevious : QuizEvents()
    object Submit : QuizEvents()
    data class OnChoiceChange(val selectedAnswer : String) : QuizEvents()

}
