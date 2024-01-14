package com.joel.composeflix.core.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.joel.composeflix.core.datasource.local.db.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie_Table")
    fun fetchAll() : Flow<List<MovieEntity>>
    @Upsert
    suspend fun insertMovies(data : List<MovieEntity>)

}