package com.acuna.soundscape.data.local.entities.details

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenreEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)