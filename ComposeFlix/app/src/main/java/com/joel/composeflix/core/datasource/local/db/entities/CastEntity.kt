package com.joel.composeflix.core.datasource.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Cast_Table")
data class CastEntity(
    @PrimaryKey val id : Int,
    val name : String,
    val image : Int,
)