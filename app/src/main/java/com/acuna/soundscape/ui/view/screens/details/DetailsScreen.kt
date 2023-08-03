package com.acuna.soundscape.ui.view.screens.details

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.acuna.soundscape.R
import com.acuna.soundscape.domain.model.AlbumDetailsDTO
import com.acuna.soundscape.domain.model.AlbumDetailsUiState
import com.acuna.soundscape.domain.model.TrackUiModel
import com.acuna.soundscape.ui.view.Error
import com.acuna.soundscape.ui.view.Loading
import com.acuna.soundscape.ui.view.widgets.ColoredPill
import com.acuna.soundscape.utils.toMinutes
import java.lang.Float.min

@Composable
fun DetailsScreen(
    id: String?,
    viewModel: DetailsViewModel,
    onBackAction: () -> Unit
) {
    val uiState by viewModel.albumDetailsUiState.collectAsStateWithLifecycle()

    if (!id.isNullOrEmpty()) {
        LaunchedEffect(id) {
            viewModel.setEvent(Event.GetAlbumsDetailsById(id))
        }

        when (uiState) {
            AlbumDetailsUiState.Error -> Error()
            AlbumDetailsUiState.Loading -> Loading()
            is AlbumDetailsUiState.Success -> DetailsContent(
                (uiState as AlbumDetailsUiState
                .Success).albumDetailsDTO
            ) {
                onBackAction()
            }
        }
    }
}

@Composable
fun DetailsContent(
    albumDetailsDTO: AlbumDetailsDTO,
    onBackAction: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = albumDetailsDTO.albumCoverUrl,
                contentDescription = stringResource(R.string.album_cover_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .graphicsLayer {
                        alpha = min(1f, 1 - (scrollState.value / 800f))
                        translationY = -scrollState.value * 0.6f
                    }
            )
            AsyncImage(
                model = albumDetailsDTO.artistImgUrl,
                contentDescription = stringResource(R.string.album_cover_image),
                modifier = Modifier
                    .size(212.dp)
                    .offset(y = 86.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .border(8.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .align(Alignment.BottomEnd)
            )
        }

        Spacer(modifier = Modifier.height(64.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            if (albumDetailsDTO.explicitLyrics) {
                ColoredPill(text = stringResource(R.string.explicit_label), Color.Red)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = albumDetailsDTO.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraLight
            )

            Text(
                text = albumDetailsDTO.artist,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "release date: ${albumDetailsDTO.releaseDate}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "duration: ${albumDetailsDTO.duration.toMinutes} min",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "genres: ${albumDetailsDTO.genres.joinToString(", ")}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Track List
            Text(
                text = stringResource(R.string.track_list),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            albumDetailsDTO.tracks.forEachIndexed { index, track ->
                TrackItem(index.inc(), track)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onBackAction,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = stringResource(R.string.back_to_list), color = Color.White)
            }
        }
    }
}

@Preview
@Composable
private fun TrackItem(index: Int = 1, track: TrackUiModel = TrackUiModel(stringResource(R.string.title), 3660)) {
    Column(modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$index. ${track.title}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            ColoredPill(text = track.duration.toMinutes)
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(thickness = 1.dp, color = Color.Gray)
    }
}

