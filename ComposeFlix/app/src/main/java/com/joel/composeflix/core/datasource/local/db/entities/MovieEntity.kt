package com.joel.composeflix.core.datasource.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie_Table")
data class MovieEntity(
    @PrimaryKey val id : Int,
    val title : String,
    val duration : Int,
    val image : String,
    val description : String,
    val rating : Int,
    val releaseDate : String,
    val thumbsUp : Int,
    val thumbsDown : Int,
    val pgRating : Boolean,
    val popularity : Int,
    val filmType : String
)

