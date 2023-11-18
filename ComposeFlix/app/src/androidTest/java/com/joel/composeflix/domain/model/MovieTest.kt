package com.joel.composeflix.domain.model

import androidx.compose.ui.graphics.Color
import com.joel.composeflix.core.domain.model.Movie
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieTest {

    private val mostPopular = Movie(
        id = 1,
        title = "Oppenheimer",
        image = 43536,
        popularity = 67
    )
    private val mediumPopularity = Movie(
        id = 2,
        title = "Amazing Spider-man",
        image = 7484,
        popularity = 45
    )

    private val leastPopular = Movie(
        id = 3,
        title = "Saint Antonio's",
        image = 46648,
        popularity = 19
    )

    @Test
    fun categorizeMostPopular_isCorrect(){
        val popularityCategory = mostPopular.filterPopularityByColor()
        assertEquals(popularityCategory, Color.Green)
    }

    @Test
    fun categorizeLeastPopular_isCorrect(){
        val popularityCategory = leastPopular.filterPopularityByColor()
        assertEquals(popularityCategory, Color.Red)
    }

    @Test
    fun categorizeMediumPopular_isCorrect(){
        val popularityCategory = mediumPopularity.filterPopularityByColor()
        assertEquals(popularityCategory, Color.Yellow)
    }

}