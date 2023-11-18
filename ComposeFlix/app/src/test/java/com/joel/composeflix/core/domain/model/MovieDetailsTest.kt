package com.joel.composeflix.core.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class MovieDetailsTest {

    private val details = MovieDetails(
        id = 1,
        title = "",
        thumbsDown = 2,
        thumbsUp = 5,
        description = "",
        image = 1,
        duration = 181,
        pgRating = "",
        releaseDate = "",
        genres = emptyList(),
        rating = 24
    )

    @Test
    fun convertTime_isCorrect(){
        val time  = details.convertDurationToTime()
        assertEquals("03:01", time)
    }
}