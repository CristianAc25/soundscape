package com.acuna.soundscape.data.local.entities.common

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArtistEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val type: String
)