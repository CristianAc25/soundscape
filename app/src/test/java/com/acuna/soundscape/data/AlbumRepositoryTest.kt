import com.acuna.soundscape.data.AlbumRepository
import com.acuna.soundscape.data.remote.entities.common.Artist
import com.acuna.soundscape.data.remote.entities.details.AlbumDetails
import com.acuna.soundscape.data.remote.entities.details.Genres
import com.acuna.soundscape.data.remote.entities.details.Tracks
import com.acuna.soundscape.data.remote.services.AlbumService
import com.acuna.soundscape.domain.model.AlbumDetailsUiState
import com.acuna.soundscape.domain.model.toUiModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AlbumRepositoryTest {

    private lateinit var albumRepository: AlbumRepository
    private val albumService: AlbumService = mockk()

    @Before
    fun setup() {
        albumRepository = AlbumRepository(albumService, albumService, mockk())
    }

    @Test
    fun `getAlbumDetailsById returns AlbumDetailsUiState Success`() = runTest {
        // Arrange
        val albumId = "123"

        val artist = Artist(
            id = "12345",
            name = "Mock Artist",
            picture = "https://example.com/artist_picture.png",
            picture_big = "https://example.com/artist_picture_big.png",
            picture_medium = "https://example.com/artist_picture_medium.png",
            picture_small = "https://example.com/artist_picture_small.png",
            picture_xl = "https://example.com/artist_picture_xl.png",
            type = "artist_type"
        )

        val albumDetails = AlbumDetails(
            artist = artist,
            cover = "mock_cover_url",
            cover_big = "mock_cover_big_url",
            cover_medium = "mock_cover_medium_url",
            cover_small = "mock_cover_small_url",
            cover_xl = "mock_cover_xl_url",
            duration = 180,
            genres = Genres(emptyList()),
            id = "mock_album_id",
            label = "Mock Label",
            link = "mock_album_link",
            record_type = "Mock Record Type",
            release_date = "2023-08-01",
            explicit_lyrics = true,
            share = "mock_share_link",
            title = "Mock Album Title",
            tracks = Tracks(emptyList()),
            type = "Mock Type"
        )

        // Stubbing the behavior of the albumService
        coEvery { albumService.getAlbumDetailsById(albumId) } returns albumDetails

        // Act
        val resultFlow = albumRepository.getAlbumDetailsById(albumId)

        // Assert
        resultFlow.collect { result ->
            assertEquals(toUiModel(albumDetails), result)
        }
    }

    @Test
    fun `getAlbumDetailsById returns AlbumDetailsUiState Error`() = runTest {
        // Arrange
        val albumId = "123"
        val throwable = Throwable("Some error")

        // Stubbing the behavior of the albumService to throw an exception
        coEvery { albumService.getAlbumDetailsById(albumId) } throws throwable

        // Act
        val resultFlow = albumRepository.getAlbumDetailsById(albumId)

        // Assert
        resultFlow.collect { result ->
            assertEquals(AlbumDetailsUiState.Error, result)
        }
    }
}
