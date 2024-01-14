package com.joel.composeflix.core.domain.model

data class MovieDomainModel(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)


data class Movie(
    val movieId : Int,
    val title : String,
    val duration : Int,
    val filmPosterImage : String,
    val description : String,
    val genres : List<Genres>,
    val rating : Int,
    val releaseDate : String,
    val thumbsUp : Int,
    val thumbsDown : Int,
    val pgRating : Boolean,
    val popularity : Int,
)

data class Genres(
    val name : String
)