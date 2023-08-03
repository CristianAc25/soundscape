package com.acuna.soundscape.data.local.entities.details

import androidx.room.Entity

@Entity
data class GenresEntity(
    val data: List<GenreEntity>
)