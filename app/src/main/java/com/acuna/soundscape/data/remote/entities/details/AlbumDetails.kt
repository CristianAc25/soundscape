package com.acuna.soundscape.data.remote.entities.details

import com.acuna.soundscape.data.remote.entities.common.Artist

data class AlbumDetails(
    val artist: Artist,
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val duration: Int,
    val genres: Genres,
    val id: String,
    val label: String,
    val link: String,
    val record_type: String,
    val release_date: String,
    val explicit_lyrics: Boolean,
    val share: String,
    val title: String,
    val tracks: Tracks,
    val type: String
)