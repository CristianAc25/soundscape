package com.acuna.soundscape.data.mappers

import com.acuna.soundscape.data.local.entities.search.AlbumSearchEntity
import com.acuna.soundscape.data.remote.entities.search.AlbumSearch

fun AlbumSearch.toAlbumSearchRoomEntity(): AlbumSearchEntity {
    return AlbumSearchEntity(
        data = data.map { it.toAlbumRoomEntity() },
        next = next,
        total = total
    )
}