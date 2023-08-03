package com.acuna.soundscape.domain.model

import com.acuna.soundscape.data.local.entities.search.AlbumEntity
import com.acuna.soundscape.data.local.entities.search.AlbumSearchEntity
import com.acuna.soundscape.data.remote.entities.search.AlbumSearch

fun toUiModel(albumModel: AlbumSearch): AlbumUiState =
    AlbumUiState.Success(albumModel.data.map { album ->
        AlbumDTO(
            id = album.id,
            title = album.title,
            artist = album.artist.name,
            recordType = album.record_type,
            img = album.cover_big
        )
    })

fun AlbumEntity.toAlbumDto(): AlbumDTO {
    return AlbumDTO(
        id = id,
        title = title,
        artist = "Juan",
        recordType = record_type,
        img = cover_big,
    )
}