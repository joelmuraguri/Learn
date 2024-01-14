package com.joel.composeflix.core.data.repo

import com.joel.composeflix.core.domain.model.MovieDomainModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun fetchMovieInformation() : Flow<MovieDomainModel>

}