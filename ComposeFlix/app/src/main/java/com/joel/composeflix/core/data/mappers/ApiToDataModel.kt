package com.joel.composeflix.core.data.mappers

import com.joel.composeflix.core.data.model.GenreDataModel
import com.joel.composeflix.core.data.model.MovieDataModel
import com.joel.composeflix.core.data.model.MoviePageDataModel
import com.joel.composeflix.core.datasource.remote.response.MovieApiResponse
import com.joel.composeflix.core.datasource.remote.response.MovieDetailsApiResponse

object ApiToDataModel {

    fun MovieApiResponse.toData(movieApiResponse: MovieApiResponse, movieDetailsApiResponse: MovieDetailsApiResponse){
        MoviePageDataModel(
            page = movieApiResponse.page,
            movies = movieApiResponse.results.toData(movieDetailsApiResponse),
            totalPages = movieApiResponse.totalPages,
            totalResults = movieApiResponse.totalResults
        )
    }


    private fun List<MovieApiResponse.Movie>.toData(movieDetailsApiResponse: MovieDetailsApiResponse) = map {
        MovieDataModel(
            movieId = movieDetailsApiResponse.id,
            title = movieDetailsApiResponse.title,
            popularity = movieDetailsApiResponse.popularity.toInt(),
            filmType = it.mediaType!!,
            duration = movieDetailsApiResponse.runtime,
            description = movieDetailsApiResponse.overview,
            releaseDate = movieDetailsApiResponse.releaseDate,
            filmPosterImage = movieDetailsApiResponse.posterPath,
            genres = movieDetailsApiResponse.genres.toData(),
            pgRating = movieDetailsApiResponse.adult,
            thumbsUp = movieDetailsApiResponse.voteCount,
            thumbsDown = movieDetailsApiResponse.voteAverage.toInt(),
            rating = movieDetailsApiResponse.popularity.toInt()
        )
    }


    private fun List<MovieDetailsApiResponse.Genre>.toData(): List<GenreDataModel> {
       return map {
           GenreDataModel(
               title = it.name
           )
       }
    }

}