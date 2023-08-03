package com.acuna.soundscape.data.mappers

import com.acuna.soundscape.data.local.entities.search.AlbumEntity
import com.acuna.soundscape.data.remote.entities.search.Album
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class AlbumMapperTest {

    @Test
    fun testToAlbumRoomEntity() {
        // Arrange
        val album = Album(
            id = "123",
            artist = mockk(),
            cover_big = "cover_big_url",
            cover_medium = "cover_medium_url",
            cover_small = "cover_small_url",
            link = "album_link",
            record_type = "album_record_type",
            title = "Album Title",
            type = "album_type"
        )

        every { album.artist.name } returns "Artist Name"

        // Act
        val albumEntity = album.toAlbumRoomEntity(456)

        // Assert
        assertEquals(456, albumEntity.id)
        assertEquals("123", albumEntity.albumId)
        assertEquals("Album Title", albumEntity.title)
        assertEquals("album_record_type", albumEntity.record_type)
        assertEquals("album_type", albumEntity.type)
        assertEquals("cover_big_url", albumEntity.cover_big)
        assertEquals("cover_medium_url", albumEntity.cover_medium)
        assertEquals("cover_small_url", albumEntity.cover_small)
        assertEquals("album_link", albumEntity.link)
        assertEquals("Artist Name", albumEntity.artistName)
    }
}
