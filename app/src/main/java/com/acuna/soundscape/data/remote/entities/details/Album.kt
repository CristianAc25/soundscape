package com.acuna.soundscape.data.remote.entities.details

data class Album(
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val id: String,
    val md5_image: String,
    val title: String,
    val tracklist: String,
    val type: String
)