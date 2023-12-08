package com.joel.composeflix.core.datasource.remote.api

import com.joel.composeflix.core.datasource.remote.response.MovieApiResponse
import com.joel.composeflix.core.datasource.remote.response.MovieDetailsApiResponse
import com.joel.composeflix.core.datasource.remote.utils.NetworkResult

interface FilmRemoteSource {

    suspend fun getTrendingMovies() : NetworkResult<MovieApiResponse>
    suspend fun getMovieDetails(filmId : Int) : NetworkResult<MovieDetailsApiResponse>
}

