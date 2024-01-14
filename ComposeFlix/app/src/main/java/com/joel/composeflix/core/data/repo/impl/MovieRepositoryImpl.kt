package com.joel.composeflix.core.data.repo.impl

import com.joel.composeflix.core.data.repo.MovieRepository
import com.joel.composeflix.core.datasource.local.db.dao.GenreDao
import com.joel.composeflix.core.datasource.local.db.dao.MovieDao
import com.joel.composeflix.core.datasource.local.db.dao.MoviePageDao
import com.joel.composeflix.core.datasource.remote.api.FilmRemoteSource
import com.joel.composeflix.core.domain.model.MovieDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviePageDao: MoviePageDao,
    private val movieDao: MovieDao,
    private val genreDao: GenreDao,
    private val filmRemoteSource: FilmRemoteSource
) : MovieRepository {

    override suspend fun fetchMovieInformation(): Flow<MovieDomainModel> {
        TODO("Not yet implemented")
    }
}