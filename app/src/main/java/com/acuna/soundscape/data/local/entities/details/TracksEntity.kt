package com.acuna.soundscape.data.local.entities.details

import androidx.room.Entity

@Entity
data class TracksEntity(
    val data: List<TrackEntity>
)