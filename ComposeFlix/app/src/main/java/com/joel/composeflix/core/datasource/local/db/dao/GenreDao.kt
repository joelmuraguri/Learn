package com.joel.composeflix.core.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.joel.composeflix.core.datasource.local.db.entities.GenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    @Query("SELECT * FROM Genre_Table")
    fun fetchAll() : Flow<List<GenreEntity>>

    @Upsert
    suspend fun insert(genres : List<GenreEntity>)
}