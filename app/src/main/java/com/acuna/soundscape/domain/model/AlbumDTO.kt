package com.acuna.soundscape.domain.model

data class AlbumDTO(
    val id: Int,
    val albumId: String,
    val title: String,
    val artist: String,
    val recordType: String,
    val img: String?
)
