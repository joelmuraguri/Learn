package com.joel.composeflix.core.datasource.remote.api

import com.joel.composeflix.core.datasource.remote.response.CastApiResponse
import com.joel.composeflix.core.datasource.remote.response.MovieApiResponse
import com.joel.composeflix.core.datasource.remote.response.MovieDetailsApiResponse
import com.joel.composeflix.core.datasource.remote.response.TvShowApiResponse
import com.joel.composeflix.core.datasource.remote.response.TvShowDetailsApiResponse
import com.joel.composeflix.core.datasource.remote.utils.NetworkResult

interface FilmRemoteSource {

    suspend fun getTrendingMovies() : NetworkResult<MovieApiResponse>

    suspend fun getTrendingTvShows() : NetworkResult<TvShowApiResponse>

    suspend fun getCastForTvShows(filmId : Int)  : NetworkResult<CastApiResponse>

    suspend fun getCastForMovies(filmId : Int)  : NetworkResult<CastApiResponse>

    suspend fun getMovieDetails(filmId : Int) : NetworkResult<MovieDetailsApiResponse>

    suspend fun getTvShowsDetails(filmId : Int) : NetworkResult<TvShowDetailsApiResponse>

    suspend fun getSimilarMovies(filmId : Int) : NetworkResult<MovieApiResponse>

    suspend fun getSimilarTvShows(filmId : Int) : NetworkResult<TvShowApiResponse>
}

