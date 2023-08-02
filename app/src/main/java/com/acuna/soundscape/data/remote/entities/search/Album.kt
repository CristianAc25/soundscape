package com.acuna.soundscape.data.remote.entities.search

import com.acuna.soundscape.data.remote.entities.common.Artist

data class Album(
    val artist: Artist,
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val id: String,
    val link: String,
    val record_type: String,
    val title: String,
    val type: String
)