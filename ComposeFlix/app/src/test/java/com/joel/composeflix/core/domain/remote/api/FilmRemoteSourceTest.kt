package com.joel.composeflix.core.domain.remote.api

import com.joel.composeflix.core.datasource.remote.api.FilmRemoteSourceImpl
import com.joel.composeflix.core.datasource.remote.response.MovieApiResponse
import com.joel.composeflix.core.datasource.remote.response.MovieDetailsApiResponse
import com.joel.composeflix.core.datasource.remote.utils.HttpClientFactory
import com.joel.composeflix.core.datasource.remote.utils.NetworkResult
import com.joel.composeflix.core.domain.remote.utils.MovieApiResponseModel
import com.joel.composeflix.core.domain.remote.utils.MovieDetailsApiResponseModel
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

}