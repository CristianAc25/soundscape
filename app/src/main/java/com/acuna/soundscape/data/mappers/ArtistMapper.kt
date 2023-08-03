package com.acuna.soundscape.data.mappers

import com.acuna.soundscape.data.local.entities.common.ArtistEntity
import com.acuna.soundscape.data.remote.entities.common.Artist

fun Artist.toRoomArtistEntity(): ArtistEntity {
    return ArtistEntity(
        id = id,
        name = name,
        picture_big = picture_big,
        picture_medium = picture_medium,
        picture_small = picture_small,
        type = type
    )
}