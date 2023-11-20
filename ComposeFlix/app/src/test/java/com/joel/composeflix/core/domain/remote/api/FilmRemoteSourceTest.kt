package com.joel.composeflix.core.domain.remote.api

import com.joel.composeflix.core.datasource.remote.api.FilmRemoteSourceImpl
import com.joel.composeflix.core.datasource.remote.response.CastApiResponse
import com.joel.composeflix.core.datasource.remote.response.MovieApiResponse
import com.joel.composeflix.core.datasource.remote.response.MovieDetailsApiResponse
import com.joel.composeflix.core.datasource.remote.response.TvShowApiResponse
import com.joel.composeflix.core.datasource.remote.response.TvShowDetailsApiResponse
import com.joel.composeflix.core.datasource.remote.utils.HttpClientFactory
import com.joel.composeflix.core.datasource.remote.utils.NetworkResult
import com.joel.composeflix.core.domain.remote.utils.CastDetailsApiResponseModel
import com.joel.composeflix.core.domain.remote.utils.MovieApiResponseModel
import com.joel.composeflix.core.domain.remote.utils.MovieDetailsApiResponseModel
import com.joel.composeflix.core.domain.remote.utils.TvApiResponseModel
import com.joel.composeflix.core.domain.remote.utils.TvDetailsApiResponseModel
import com.joel.composeflix.core.domain.remote.utils.SimilarMovieApiResponseModel
import com.joel.composeflix.core.domain.remote.utils.SimilarTvShowApiResponseModel
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test


class FilmRemoteSourceTest {

    @Test
    fun `give result success for MovieApiResponse`(){
        val mockEngine = MockEngine{
            respond(
                content = MovieApiResponseModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {
            // When
            val response : NetworkResult<MovieApiResponse> = FilmRemoteSourceImpl(client, "").getTrendingMovies()
            val result : MovieApiResponse = when(response){
                is NetworkResult.Error -> throw AssertionError("Unexpected error: ${response.message}")
                is NetworkResult.Success -> response.data
            }

            //Then
            val expected = Json.decodeFromString<MovieApiResponse>(MovieApiResponseModel)
            assertEquals(expected, result)
        }
    }

    @Test
    fun `give success result for MovieDetails`(){
        val mockEngine = MockEngine{
            respond(
                content = MovieDetailsApiResponseModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().createEngine(mockEngine)

        runBlocking {
            // When
            val movieId = 872585
            val result : NetworkResult<MovieDetailsApiResponse> = FilmRemoteSourceImpl(client, "").getMovieDetails(movieId)
            val data : MovieDetailsApiResponse = when(result){
                is NetworkResult.Error -> throw AssertionError("Unexpected error: ${result.message}")
                is NetworkResult.Success -> result.data
            }

            //Then
            val expected = Json.decodeFromString<MovieDetailsApiResponse>(MovieDetailsApiResponseModel)
            assertEquals(expected, data)

        }
    }


//    @Test
//    fun  `give success for TvShowApiResponse`(){
//        val mockEngine = MockEngine{
//            respond(
//                content = TvApiResponseModel,
//                headers = headersOf(HttpHeaders.ContentType,"application/json")
//            )
//
//        }
//
//        val client = HttpClientFactory().createEngine(mockEngine)
//
//        runBlocking {
//            val data : TvShowApiResponse = when(val response : NetworkResult<TvShowApiResponse> = FilmRemoteSourceImpl(client, "").getTrendingTvShows()){
//                is NetworkResult.Error -> throw AssertionError("Unexpected Error : ${response.message}")
//                is NetworkResult.Success -> response.data
//            }
//
//            val expected = Json.decodeFromString<TvShowApiResponse>(TvApiResponseModel)
//            assertEquals(expected, data)
//        }
//
//    }
//
//    @Test
//    fun  `give success for TVShowDetails`(){
//        val mockEngine = MockEngine{
//            respond(
//                content = TvDetailsApiResponseModel,
//                headers = headersOf(HttpHeaders.ContentType, "application/json")
//            )
//        }
//
//        val client = HttpClientFactory().createEngine(mockEngine)
//
//        runBlocking {
//            // When
//            val tvShowId = 37854
//            val result : NetworkResult<TvShowDetailsApiResponse> = FilmRemoteSourceImpl(client, "").getTvShowsDetails(tvShowId)
//            val data : TvShowDetailsApiResponse = when(result){
//                is NetworkResult.Error -> throw AssertionError("Unexpected error: ${result.message}")
//                is NetworkResult.Success -> result.data
//            }
//
//
//            //Then
//            val expected = Json.decodeFromString<TvShowDetailsApiResponse>(TvDetailsApiResponseModel)
//            assertEquals(expected, data)
//
//        }
//    }
//
//    @Test
//    fun `give success for TvShowCast`(){
//        val mockEngine = MockEngine{
//            respond(
//                content = CastDetailsApiResponseModel,
//                headers =  headersOf(HttpHeaders.ContentType, "application/json")
//            )
//        }
//
//        val client = HttpClientFactory().createEngine(mockEngine)
//
//        runBlocking {
//            val tvId = 37854
//            val response : NetworkResult<CastApiResponse> = FilmRemoteSourceImpl(client, "").getCastForTvShows(filmId = tvId)
//            val data : CastApiResponse = when(response){
//                is NetworkResult.Error -> throw AssertionError("Unexpected Error ${response.message}")
//                is NetworkResult.Success -> response.data
//            }
//
//            val expected = Json.decodeFromString<CastApiResponse>(CastDetailsApiResponseModel)
//            assertEquals(expected, data)
//        }
//    }
//
//    @Test
//    fun `give success for MovieCast`(){
//        val mockEngine = MockEngine{
//            respond(
//                content = CastDetailsApiResponseModel,
//                headers =  headersOf(HttpHeaders.ContentType, "application/json")
//            )
//        }
//
//        val client = HttpClientFactory().createEngine(mockEngine)
//
//        runBlocking {
//            val movieId = 243
//            val response : NetworkResult<CastApiResponse> = FilmRemoteSourceImpl(client, "").getCastForMovies(filmId = movieId)
//            val data : CastApiResponse = when(response){
//                is NetworkResult.Error -> throw AssertionError("Unexpected Error ${response.message}")
//                is NetworkResult.Success -> response.data
//            }
//
//            val expected = Json.decodeFromString<CastApiResponse>(CastDetailsApiResponseModel)
//            assertEquals(expected, data)
//        }
//    }
//
//    @Test
//    fun `give success for similar movies`(){
//        val movieId = 872585
//        val mockEngine = MockEngine{
//            respond(
//                content = SimilarMovieApiResponseModel,
//                headers = headersOf(HttpHeaders.ContentType, "application/json")
//            )
//        }
//
//        val client = HttpClientFactory().createEngine(mockEngine)
//
//        runBlocking{
//            val response : NetworkResult<MovieApiResponse> = FilmRemoteSourceImpl(client, "").getSimilarMovies(movieId)
//            val data : MovieApiResponse = when(response){
//                is NetworkResult.Error -> throw AssertionError("Unexpected error occurred ${response.message}")
//                is NetworkResult.Success -> response.data
//            }
//
//            val expected = Json.decodeFromString<MovieApiResponse>(SimilarMovieApiResponseModel)
//            assertEquals(expected, data)
//        }
//
//    }
//
//    @Test
//    fun `give success for similar tvShows`(){
//        val tvId = 37854
//        val mockEngine = MockEngine{
//            respond(
//                content = SimilarTvShowApiResponseModel,
//                headers = headersOf(HttpHeaders.ContentType, "application/json")
//            )
//        }
//
//        val client = HttpClientFactory().createEngine(mockEngine)
//
//        runBlocking{
//
//            val response : NetworkResult<TvShowApiResponse> = FilmRemoteSourceImpl(client, "").getSimilarTvShows(tvId)
//            val data : TvShowApiResponse = when(response){
//                is NetworkResult.Error -> throw AssertionError("Unexpected error ${response.message}")
//                is NetworkResult.Success -> response.data
//            }
//
//            val expected = Json.decodeFromString<TvShowApiResponse>(SimilarTvShowApiResponseModel)
//            assertEquals(expected, data)
//        }
//    }
//
//    private fun sharedMockConfiguration(){
//
//    }

}