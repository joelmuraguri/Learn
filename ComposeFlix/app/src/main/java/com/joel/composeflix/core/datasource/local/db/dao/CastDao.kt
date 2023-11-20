package com.joel.composeflix.core.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.joel.composeflix.core.datasource.local.db.entities.CastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CastDao {

    @Query("SELECT * FROM Cast_Table where id=:filmId")
    fun getAllCastsForFilm(filmId : Int) : Flow<List<CastEntity>>

    @Upsert
    suspend fun insertCasts(castEntity: CastEntity)


}