package com.joel.composeflix.core.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.joel.composeflix.core.datasource.local.db.entities.FilmEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FilmDao {

    @Query("SELECT * FROM Film_Table WHERE filmType=:filmType")
    fun getAllFilms(filmType : String) : Flow<List<FilmEntity>>

    @Upsert
    suspend fun upsertFilms(filmEntity: FilmEntity)

}