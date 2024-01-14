package com.joel.composeflix.core.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.joel.composeflix.core.datasource.local.db.entities.MovieEntityPage
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviePageDao {

    @Query("SELECT * FROM MovieEntityPage_Table")
    fun fetchAll() : Flow<MovieEntityPage>

    @Upsert
    suspend fun insertMoviePage(data : MovieEntityPage)
}