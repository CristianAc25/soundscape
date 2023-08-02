package com.acuna.soundscape.domain.model

import com.acuna.soundscape.data.remote.entities.details.AlbumDetails

fun toUiModel(model: AlbumDetails): AlbumDetailsUiState =
    AlbumDetailsUiState.Success(
        AlbumDetailsUiModel(
            id = model.id,
            title = model.title,
            artist = model.artist.name,
            recordType = model.record_type,
            albumCoverUrl = model.cover_big,
            artistImgUrl = model.artist.picture_big,
            releaseDate = model.release_date,
            explicitLyrics = model.explicit_lyrics,
            duration = model.duration,
            genres = model.genres.data.map { it.name },
            tracks = model.tracks.data.map { TrackUiModel(it.title, it.duration) }
        ))