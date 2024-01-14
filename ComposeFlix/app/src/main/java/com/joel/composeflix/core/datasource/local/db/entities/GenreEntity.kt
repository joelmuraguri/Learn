package com.joel.composeflix.core.datasource.local.db.entities

import androidx.room.Entity

@Entity(tableName = "Genre_Table")
data class GenreEntity(
    val name : String
)