package com.acuna.soundscape.data.local.entities.search

import androidx.room.Entity

@Entity
data class AlbumSearchEntity(
    val data: List<AlbumEntity>,
    val next: String,
    val total: Int
)