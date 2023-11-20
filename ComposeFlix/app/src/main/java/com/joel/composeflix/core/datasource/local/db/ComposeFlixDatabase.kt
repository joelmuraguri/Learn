package com.joel.composeflix.core.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.joel.composeflix.core.datasource.local.db.dao.CastDao
import com.joel.composeflix.core.datasource.local.db.dao.FilmDao
import com.joel.composeflix.core.datasource.local.db.entities.CastEntity
import com.joel.composeflix.core.datasource.local.db.entities.FilmEntity
import com.joel.composeflix.core.datasource.local.db.converters.StringListConverter


@Database(entities = [FilmEntity::class,CastEntity::class] , version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class ComposeFlixDatabase() : RoomDatabase() {

    abstract fun castDao() : CastDao
    abstract  fun filmDao() : FilmDao

}