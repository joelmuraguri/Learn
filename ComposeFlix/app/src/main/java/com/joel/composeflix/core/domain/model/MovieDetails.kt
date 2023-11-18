package com.joel.composeflix.core.domain.model

data class MovieDetails(
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
    val pgRating : String
){

    fun convertDurationToTime(): String {
        val hours = duration / 60
        val minutes = duration % 60

        return String.format("%02d:%02d", hours, minutes)
    }
}
