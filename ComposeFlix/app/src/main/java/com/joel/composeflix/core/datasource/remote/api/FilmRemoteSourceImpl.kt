package com.joel.composeflix.core.datasource.remote.api

import com.joel.composeflix.core.datasource.remote.response.CastApiResponse
import com.joel.composeflix.core.datasource.remote.response.MovieApiResponse
import com.joel.composeflix.core.datasource.remote.response.MovieDetailsApiResponse
import com.joel.composeflix.core.datasource.remote.response.TvShowApiResponse
import com.joel.composeflix.core.datasource.remote.response.TvShowDetailsApiResponse
import com.joel.composeflix.core.datasource.remote.utils.NetworkResult
import com.joel.composeflix.core.datasource.remote.utils.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class FilmRemoteSourceImpl @Inject constructor(
    private val client : HttpClient,
    private val apiKey : String
) : FilmRemoteSource {

    override suspend fun getTrendingMovies(): NetworkResult<MovieApiResponse> = safeApiCall {
        val url = "https://api.themoviedb.org/3/trending/movie/day"
        client.get(url){
            parameter("api_key", apiKey)
        }.body()
    }

    override suspend fun getTrendingTvShows(): NetworkResult<TvShowApiResponse> = safeApiCall {
        val url = "https://api.themoviedb.org/3/trending/tv/day"
        client.get(url){
            parameter("api_key", apiKey)
        }.body()
    }

    override suspend fun getCastForTvShows(filmId: Int): NetworkResult<CastApiResponse> = safeApiCall {
        val url = "https://api.themoviedb.org/3/tv/$filmId/credits"
        client.get(url){
            parameter("api_key", apiKey)
        }.body()
    }

    override suspend fun getCastForMovies(filmId: Int): NetworkResult<CastApiResponse> = safeApiCall {
        val url = "https://api.themoviedb.org/3/movie/$filmId/credits"
        client.get(url){
            parameter("api_key", apiKey)
        }.body()
    }

    override suspend fun getMovieDetails(filmId: Int): NetworkResult<MovieDetailsApiResponse> = safeApiCall{
        val url = "https://api.themoviedb.org/3/movie/$filmId"
        client.get(url){
            parameter("api_key", apiKey)
        }.body()
    }

    override suspend fun getTvShowsDetails(filmId: Int): NetworkResult<TvShowDetailsApiResponse> = safeApiCall {
        val url = "https://api.themoviedb.org/3/tv/$filmId"
        client.get(url){
            parameter("api_key", apiKey)
        }.body()
    }

    override suspend fun getSimilarMovies(filmId: Int): NetworkResult<MovieApiResponse> = safeApiCall {
        val url = "https://api.themoviedb.org/3/movie/$filmId/similar"
        client.get(url){
            parameter("api_key", apiKey)
        }.body()
    }

    override suspend fun getSimilarTvShows(filmId: Int): NetworkResult<TvShowApiResponse> = safeApiCall {
        val url = "https://api.themoviedb.org/3/tv/$filmId/similar"
        client.get(url){
            parameter("api_key", apiKey)
        }.body()
    }
}