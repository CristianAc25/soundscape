package com.acuna.soundscape.data.local.entities.search

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlbumEntity(
    @PrimaryKey
    val id: Int,
    val albumId: String,
    val artistName: String,
    val cover_big: String?,
    val cover_medium: String?,
    val cover_small: String?,
    val link: String,
    val record_type: String,
    val title: String,
    val type: String
)