package com.acuna.soundscape.data.remote.entities.details

data class Track(
    val album: Album,
    val artist: ArtistSummary,
    val duration: Int,
    val explicit_content_cover: Int,
    val explicit_content_lyrics: Int,
    val explicit_lyrics: Boolean,
    val id: String,
    val link: String,
    val md5_image: String,
    val preview: String,
    val rank: String,
    val readable: Boolean,
    val title: String,
    val title_short: String,
    val title_version: String,
    val type: String
)