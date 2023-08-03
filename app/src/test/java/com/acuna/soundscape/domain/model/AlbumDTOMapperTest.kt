
import com.acuna.soundscape.data.local.entities.search.AlbumEntity
import com.acuna.soundscape.domain.model.AlbumDTO
import com.acuna.soundscape.domain.model.toAlbumDto
import org.junit.Assert.assertEquals
import org.junit.Test

class AlbumEntityMapperTest {

    @Test
    fun `toAlbumDto maps AlbumEntity to AlbumDTO`() {
        // Arrange
        val albumEntity = AlbumEntity(
            id = 1,
            albumId = "album_123",
            artistName = "Artist Name",
            cover_big = "cover_big_url",
            cover_medium = "cover_medium_url",
            cover_small = "cover_small_url",
            link = "album_link",
            record_type = "album_record_type",
            title = "Album Title",
            type = "album_type"
        )

        // Act
        val result = albumEntity.toAlbumDto()

        // Assert
        val expectedAlbumDto = AlbumDTO(
            id = 1,
            albumId = "album_123",
            title = "Album Title",
            artist = "Artist Name",
            recordType = "album_record_type",
            img = "cover_big_url"
        )
        assertEquals(expectedAlbumDto, result)
    }

    @Test
    fun `toAlbumDto handles null cover_big`() {
        // Arrange
        val albumEntity = AlbumEntity(
            id = 1,
            albumId = "album_123",
            artistName = "Artist Name",
            cover_big = null, // cover_big is null in this case
            cover_medium = "cover_medium_url",
            cover_small = "cover_small_url",
            link = "album_link",
            record_type = "album_record_type",
            title = "Album Title",
            type = "album_type"
        )

        // Act
        val result = albumEntity.toAlbumDto()

        // Assert
        val expectedAlbumDto = AlbumDTO(
            id = 1,
            albumId = "album_123",
            title = "Album Title",
            artist = "Artist Name",
            recordType = "album_record_type",
            img = null // img should be null when cover_big is null
        )
        assertEquals(expectedAlbumDto, result)
    }
}
