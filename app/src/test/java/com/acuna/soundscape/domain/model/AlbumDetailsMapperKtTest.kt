import com.acuna.soundscape.data.remote.entities.common.Artist
import com.acuna.soundscape.data.remote.entities.details.AlbumDetails
import com.acuna.soundscape.data.remote.entities.details.Genre
import com.acuna.soundscape.data.remote.entities.details.Track
import com.acuna.soundscape.domain.model.AlbumDetailsDTO
import com.acuna.soundscape.domain.model.AlbumDetailsUiState
import com.acuna.soundscape.domain.model.TrackUiModel
import com.acuna.soundscape.domain.model.toUiModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class AlbumMapperTest {

    @Test
    fun `toUiModel returns AlbumDetailsUiState Success`() {
        // Arrange
        val mockAlbumDetails = mockk<AlbumDetails>()

        // Stubbing the behavior of the AlbumDetails model
        every { mockAlbumDetails.id } returns "123"
        every { mockAlbumDetails.title } returns "Album Title"

        val mockArtist = mockk<Artist>()
        every { mockArtist.name } returns "Artist Name"
        every { mockAlbumDetails.artist } returns mockArtist

        // Fix the stubbing for the Artist object
        every { mockArtist.picture_big } returns "artist_picture_big_url"

        every { mockAlbumDetails.record_type } returns "album_record_type"
        every { mockAlbumDetails.cover_big } returns "cover_big_url"
        every { mockAlbumDetails.release_date } returns "2023-08-01"
        every { mockAlbumDetails.explicit_lyrics } returns false
        every { mockAlbumDetails.duration } returns 3600

        val mockGenre = mockk<Genre>()
        every { mockGenre.name } returns "Pop"
        every { mockAlbumDetails.genres.data } returns listOf(mockGenre)

        val mockTrack = mockk<Track>()
        every { mockTrack.title } returns "Track 1"
        every { mockTrack.duration } returns 180
        every { mockAlbumDetails.tracks.data } returns listOf(mockTrack)

        // Act
        val result = toUiModel(mockAlbumDetails)

        // Assert
        val expectedUiState = AlbumDetailsUiState.Success(
            AlbumDetailsDTO(
                id = "123",
                title = "Album Title",
                artist = "Artist Name",
                recordType = "album_record_type",
                albumCoverUrl = "cover_big_url",
                artistImgUrl = "artist_picture_big_url",
                releaseDate = "2023-08-01",
                explicitLyrics = false,
                duration = 3600,
                genres = listOf("Pop"),
                tracks = listOf(TrackUiModel("Track 1", 180))
            )
        )
        assertEquals(expectedUiState, result)
    }
}
