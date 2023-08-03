package com.acuna.soundscape.data.mappers

import com.acuna.soundscape.data.local.entities.search.AlbumEntity
import com.acuna.soundscape.data.remote.entities.search.Album

fun Album.toAlbumRoomEntity(): AlbumEntity {
    return AlbumEntity(
        id = id,
        //artist = artist.toRoomArtistEntity(),
        cover_big = cover_big,
        cover_medium = cover_medium,
        cover_small = cover_small,
        link = link,
        record_type = record_type,
        title = title,
        type = type
    )
}