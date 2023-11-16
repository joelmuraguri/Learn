package com.joel.jetquiz.data

import com.joel.jetquiz.R

object DataStore {

    val quizes = listOf(
        QuizModel(
            timer = 300L,
            questionTitle = "Can you name the ocean on the west coast of the Africa?",
            possibleAnswers = listOf("Mediterranean", "Indian","Atlantic", "Pacific"),
            correctAnswer = "Atlantic",
            image = R.drawable.africa_map
        ),
        QuizModel(
            timer = 300L,
            questionTitle = "How many months on the calendar have only 30 days?",
            possibleAnswers = listOf("4","5","6","2"),
            correctAnswer = "4",
            image = null
        ),
        QuizModel(
            timer = 300L,
            questionTitle = "Yellow and Blue mixed together makes what color?",
            possibleAnswers = listOf("Purple","Green", "Brown","Orange"),
            correctAnswer = "Green",
            image = null
        ),
        QuizModel(
            timer = 300L,
            questionTitle = "Mules are a cross between a horse and what other animal?",
            possibleAnswers = listOf("Donkey", "goats", "Clydesdale"),
            correctAnswer = "Donkey",
            image = R.drawable.mule
        ),
        QuizModel(
            timer = 300L,
            questionTitle = "How many sides does a Heptagon have?",
            possibleAnswers = listOf("6","7","5","8"),
            correctAnswer = "7",
            image = null
        ),
        QuizModel(
            timer = 300L,
            questionTitle = "What planet is closest to the sun in our galaxy?",
            possibleAnswers = listOf("Mercury","Saturn","Earth","Jupiter"),
            correctAnswer = "Mercury",
            image = null
        ),
        QuizModel(
            timer = 300L,
            questionTitle = "The Mona Lisa was painted by?",
            possibleAnswers = listOf("Michelangelo", "Leonardo Da Vinci", "Leonardo DiCaprio", "Vincent van Gogh"),
            correctAnswer = "Leonardo Da Vinci",
            image = R.drawable.mona_lisa
        ),
    )

//    A Geiger Counter measures what?(precipitation, radiation, )
//    What are Humans classified as?(herbivores, carnivores, autobots, mammals)
//    Liquid turning into gas is an example of what process?(perspiration, condensation, evaporation, respiration)
//    The Mona Lisa was painted by who?(Michelangelo, Leonardo Da Vinci, Leonardo DiCaprio, Vincent van Gogh)
//    Smallest Country in the world (Vatican City, Malter, Singapore, Saint Kitts)
//    Mules are a cross between a horse and what other animal?(Donkey, goats, Clydesdale,)
//    Can you name the ocean on the west coast of the Africa?(Mediterranean, Indian, Atlantic, Pacific)
//    How many months on the calendar have only 30 days?(3,2,6,4)
//    Yellow and Blue mixed together makes what color?(Purple, Green, Brown Orange)
//    How many sides does a Heptagon have?(6,7,5,8)
//    What planet is closest to the sun in our galaxy?(Mercury, Saturn, Earth, Jupiter)
}