package com.acuna.soundscape.data.local.entities.details

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.acuna.soundscape.data.local.entities.common.ArtistEntity

@Entity
data class AlbumDetailsEntity(
    @PrimaryKey
    val id: String,
    val artist: ArtistEntity,
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val duration: Int,
    val genres: GenresEntity,
    val label: String,
    val link: String,
    val record_type: String,
    val release_date: String,
    val explicit_lyrics: Boolean,
    val share: String,
    val title: String,
    val tracks: TracksEntity,
    val type: String
)