package com.acuna.soundscape.domain.model

import com.acuna.soundscape.data.remote.entities.search.AlbumSearch

fun toUiModel(albumModel: AlbumSearch): AlbumUiState =
    AlbumUiState.Success(albumModel.data.map { album ->
        AlbumUiModel(
            id = album.id,
            title = album.title,
            artist = album.artist.name,
            recordType = album.record_type,
            img = album.cover_big
        )
    })