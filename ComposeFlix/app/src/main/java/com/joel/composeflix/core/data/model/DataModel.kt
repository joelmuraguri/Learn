package com.joel.composeflix.core.data.model


data class MoviePageDataModel(
    val page: Int,
    val movies: List<MovieDataModel>,
    val totalPages: Int,
    val totalResults: Int
)

data class MovieDataModel(
    val movieId : Int,
    val title : String,
    val duration : Int,
    val filmPosterImage : String,
    val description : String,
    val genres : List<GenreDataModel>,
    val rating : Int,
    val releaseDate : String,
    val thumbsUp : Int,
    val thumbsDown : Int,
    val pgRating : Boolean,
    val popularity : Int,
    val filmType : String,
)

data class GenreDataModel(
    val title : String
)



