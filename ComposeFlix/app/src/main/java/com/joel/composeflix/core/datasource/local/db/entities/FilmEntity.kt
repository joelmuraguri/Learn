package com.joel.composeflix.core.datasource.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.joel.composeflix.core.datasource.local.db.converters.StringListConverter

@Entity(tableName = "Film_Table")
data class FilmEntity(
    @PrimaryKey val id : Int,
    val title : String,
    val duration : Int,
    val image : Int,
    val description : String,
    @TypeConverters(StringListConverter::class)
    val genres : List<String>,
    val rating : Int,
    val releaseDate : String,
    val thumbsUp : Int,
    val thumbsDown : Int,
    val pgRating : String,
    val popularity : Int,
    val filmType : String
)