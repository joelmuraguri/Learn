package com.joel.composeflix.core.domain.interfaces

import androidx.paging.PagingData
import com.joel.composeflix.core.domain.model.Cast
import com.joel.composeflix.core.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    suspend fun getTrendingFilms(filmType: FilmType) : Flow<PagingData<Film>>

    suspend fun getCastFromFilm(filmId : Int)  : Flow<PagingData<Cast>>

    suspend fun getFilmDetails(filmId : Int) : Film

    suspend fun getSimilarFilm(filmId : Int) : Flow<PagingData<Film>>

}