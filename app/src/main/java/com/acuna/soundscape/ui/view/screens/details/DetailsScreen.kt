package com.acuna.soundscape.ui.view.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.acuna.soundscape.R
import com.acuna.soundscape.domain.model.AlbumDetailsUiModel
import com.acuna.soundscape.domain.model.AlbumDetailsUiState
import com.acuna.soundscape.domain.model.TrackUiModel
import com.acuna.soundscape.ui.view.Error
import com.acuna.soundscape.ui.view.Loading
import com.acuna.soundscape.utils.toMinutes

@Composable
fun DetailsScreen(
    id: String?,
    viewModel: DetailsViewModel
) {
    val uiState by viewModel.albumDetailsUiState.collectAsStateWithLifecycle()

    if (!id.isNullOrEmpty()) {
        LaunchedEffect(id) {
            viewModel.getAlbumsDetailsById(id)
        }

        when (uiState) {
            AlbumDetailsUiState.Error -> Error()
            AlbumDetailsUiState.Loading -> Loading()
            is AlbumDetailsUiState.Success -> DetailsContent((uiState as AlbumDetailsUiState
            .Success).albumDetailsUiModel) {}
        }
    }
}

@Composable
fun DetailsContent(
    albumDetailsUiModel: AlbumDetailsUiModel,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        AsyncImage(
            model = albumDetailsUiModel.img,
            contentDescription = stringResource(R.string.album_cover_image),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = albumDetailsUiModel.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraLight,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = albumDetailsUiModel.artist,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Spacer(modifier = Modifier.width(24.dp))

        Text(
            text = "release date: ${albumDetailsUiModel.releaseDate}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "duration: ${albumDetailsUiModel.duration}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "genres: ${albumDetailsUiModel.genres.joinToString(", ")}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Track List
        Text(
            text = stringResource(R.string.track_list),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            itemsIndexed(albumDetailsUiModel.tracks) { index, track ->
                TrackItem(index, track)
                HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
            }
        }

        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Back to Album List")
        }
    }
}

@Composable
private fun TrackItem(index: Int, track: TrackUiModel) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = "$index. ${track.title}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = track.duration.toMinutes.toString(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}