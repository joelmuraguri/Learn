package com.joel.composeflix.core.datasource.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieEntityPage_Table")
data class MovieEntityPage(
    @PrimaryKey val page: Int,
    val totalPages: Int,
    val totalResults: Int
)