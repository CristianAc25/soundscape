package com.acuna.soundscape.data.local.entities.details

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrackEntity(
    @PrimaryKey
    val id: String,
    val duration: Int,
    val link: String,
    val title: String,
    val type: String
)