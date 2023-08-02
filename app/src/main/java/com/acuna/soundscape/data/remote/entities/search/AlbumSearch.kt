package com.acuna.soundscape.data.remote.entities.search

data class AlbumSearch(
    val data: List<Album>,
    val next: String,
    val total: Int
)