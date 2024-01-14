package com.joel.composeflix.core.data.mappers

import com.joel.composeflix.core.data.model.GenreDataModel
import com.joel.composeflix.core.data.model.MovieDataModel
import com.joel.composeflix.core.data.model.MoviePageDataModel
import com.joel.composeflix.core.datasource.local.db.entities.GenreEntity
import com.joel.composeflix.core.datasource.local.db.entities.MovieEntity
import com.joel.composeflix.core.datasource.local.db.entities.MovieEntityPage

object DataToDatabase {


    fun MoviePageDataModel.toEntity() : MovieEntityPage{
        return MovieEntityPage(
            page, totalPages, totalResults
        )
    }

    fun MovieDataModel.toEntity() : MovieEntity{
        return MovieEntity(
            id = movieId,
            title = title,
            description = description,
            duration = duration,
            filmType = filmType,
            image = filmPosterImage,
            pgRating = pgRating,
            popularity = popularity,
            rating = rating,
            releaseDate = releaseDate,
            thumbsDown = thumbsDown,
            thumbsUp = thumbsUp
        )
    }

    fun GenreDataModel.toEntity() : GenreEntity{
        return GenreEntity(
            name = title
        )
    }

}