package com.joel.composeflix.core.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joel.composeflix.core.datasource.local.db.dao.GenreDao
import com.joel.composeflix.core.datasource.local.db.dao.MovieDao
import com.joel.composeflix.core.datasource.local.db.dao.MoviePageDao
import com.joel.composeflix.core.datasource.local.db.entities.MovieEntity

@Database(
    entities = [
        MovieEntity::class],
    version = 1,
    exportSchema = false)
abstract class ComposeFlixDatabase : RoomDatabase() {

    abstract  fun movieDao() : MovieDao
    abstract fun genreDao() : GenreDao
    abstract fun moviePageDao() : MoviePageDao

}