package com.joel.composeflix.core.data.mappers

import com.joel.composeflix.core.data.model.GenreDataModel
import com.joel.composeflix.core.data.model.MovieDataModel
import com.joel.composeflix.core.data.model.MoviePageDataModel
import com.joel.composeflix.core.datasource.local.db.entities.GenreEntity
import com.joel.composeflix.core.datasource.local.db.entities.MovieEntity
import com.joel.composeflix.core.datasource.local.db.entities.MovieEntityPage

object DatabaseToDataModel {

    fun toData(
        movieEntityPage: MovieEntityPage,
        movieEntity: List<MovieEntity>,
        genreEntity: List<GenreEntity>
    ) : MoviePageDataModel {
        return MoviePageDataModel(
            page = movieEntityPage.page,
            movies = movieEntity.toData(genreEntity),
            totalPages = movieEntityPage.totalPages,
            totalResults = movieEntityPage.totalResults
        )
    }

    fun List<MovieEntity>.toData(genreEntity: List<GenreEntity>) = map {
        MovieDataModel(
            movieId = it.id,
            releaseDate = it.releaseDate,
            title = it.title,
            filmPosterImage = it.image,
            filmType = it.filmType,
            description = it.description,
            rating = it.rating,
            popularity = it.popularity,
            duration = it.duration,
            thumbsDown = it.thumbsDown,
            thumbsUp = it.thumbsUp,
            pgRating = it.pgRating,
            genres = genreEntity.toData()
        )
    }

    fun List<GenreEntity>.toData() = map {
        GenreDataModel(
            title = it.name
        )
    }
}