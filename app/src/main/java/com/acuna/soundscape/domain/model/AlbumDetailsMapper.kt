package com.acuna.soundscape.domain.model

import com.acuna.soundscape.data.remote.entities.details.AlbumDetails

fun toUiModel(model: AlbumDetails): AlbumDetailsUiState =
    AlbumDetailsUiState.Success(
        AlbumDetailsUiModel(
            id = model.id,
            title = model.title,
            artist = model.artist.name,
            recordType = model.record_type,
            img = model.cover_big,
            releaseDate = model.release_date,
            duration = model.duration,
            genres = model.genres.data.map { it.name },
            tracks = model.tracks.data.map { TrackUiModel(it.title, it.duration, it.id) }
        ))