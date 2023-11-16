package com.joel.jetquiz.presentation.quiz

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joel.jetquiz.data.DataStore.quizes
import com.joel.jetquiz.data.QuizModel
import com.joel.jetquiz.utils.JetQuizEvents
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel(){


    private var questionIndex = 0

    private var _isNextEnabled = mutableStateOf(false)
    val isNextEnabled : Boolean
        get() = _isNextEnabled.value

    private var _selectedAnswer = mutableStateOf<String?>(null)
    val selectedAnswer : String?
        get() = _selectedAnswer.value

    private var _quizData = mutableStateOf(createQuizData())
    val quizData : QuizData?
        get() = _quizData.value

    private val _uiEvents = Channel<JetQuizEvents>()
    val uiEvents = _uiEvents.receiveAsFlow()

    fun onEvents(events: QuizEvents){
        when(events){
            QuizEvents.ClickNext -> {
                changeQuestion(questionIndex + 1)
            }
            QuizEvents.ClickPrevious -> {
                changeQuestion(questionIndex - 1)
            }
            is QuizEvents.OnChoiceChange -> {
                _selectedAnswer.value = events.selectedAnswer
                _isNextEnabled.value = getNextEnabled()
            }
            QuizEvents.Submit -> {
                viewModelScope.launch {
                   _uiEvents.send(JetQuizEvents.Navigate("start_page_route"))
                }
            }
        }
    }

    private fun getNextEnabled(enabled : Boolean = true) : Boolean {
        val value =  if (enabled){
            _selectedAnswer.value != null
        } else {
            return false
        }
        return value
    }



    private fun changeQuestion(newQuizIndex : Int){
        questionIndex = newQuizIndex
        _quizData.value = createQuizData()
        _isNextEnabled.value = getNextEnabled()
        println("Debug: isNextEnabled = ${_isNextEnabled.value}")

    }

    private fun createQuizData() : QuizData{
        return QuizData(
            showPreviousButton = questionIndex > 0,
            showSubmitButton = questionIndex == quizes.size -1,
            questionIndex = questionIndex,
            totalQuestions = quizes.size,
            quizes = quizes
        )
    }
}

data class QuizData(
    val showPreviousButton : Boolean,
    val showSubmitButton : Boolean,
    val questionIndex : Int,
    val totalQuestions : Int,
    val quizes: List<QuizModel>
)