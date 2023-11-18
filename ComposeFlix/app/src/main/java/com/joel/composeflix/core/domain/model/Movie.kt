package com.joel.composeflix.core.domain.model

import androidx.compose.ui.graphics.Color

data class Movie(
    val id : Int,
    val title : String,
    val image : Int,
    val popularity : Int
) {

    fun filterPopularityByColor() : Color{
        return when{
            popularity < 30 -> Color.Red
            popularity in 30..60 -> Color.Yellow
            popularity in 61..100 -> Color.Green
            else -> Color.White
        }
    }

}
