package com.acuna.soundscape.domain.model

import com.acuna.soundscape.data.local.entities.search.AlbumEntity

fun AlbumEntity.toAlbumDto(): AlbumDTO {
    return AlbumDTO(
        id = id,
        albumId = albumId,
        title = title,
        artist = artistName,
        recordType = record_type,
        img = cover_big,
    )
}