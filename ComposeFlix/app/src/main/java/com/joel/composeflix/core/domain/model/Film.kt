package com.joel.composeflix.core.domain.model

import androidx.compose.ui.graphics.Color

data class Film(
    val id : Int,
    val title : String,
    val image : Int,
    val description : String,
    val duration : Int,
    val genres : List<String>,
    val rating : Int,
    val releaseDate : String,
    val thumbsUp : Int,
    val thumbsDown : Int,
    val pgRating : String,
    val popularity : Int
){

    fun convertDurationToTime(): String {
        val hours = duration / 60
        val minutes = duration % 60

        return String.format("%02d:%02d", hours, minutes)
    }

    fun filterPopularityByColor() : Color {
        return when{
            popularity < 30 -> Color.Red
            popularity in 30..60 -> Color.Yellow
            popularity in 61..100 -> Color.Green
            else -> Color.White
        }
    }
}
