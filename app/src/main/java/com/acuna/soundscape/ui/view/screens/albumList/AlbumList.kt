package com.acuna.soundscape.ui.view.screens.albumList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.acuna.soundscape.R
import com.acuna.soundscape.domain.model.AlbumUiModel

@Composable
fun AlbumList(
    albumList: List<AlbumUiModel>,
    onClick: (id: String) -> Unit
) {

    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        LazyColumn() {
            items(count = albumList.size, itemContent = { index ->
                AlbumItem(
                    id = albumList[index].id,
                    name = albumList[index].title,
                    artist = albumList[index].artist,
                    recordType = albumList[index].recordType,
                    img = albumList[index].img,
                    onClick = onClick
                )
            })
        }
    }
}

@Composable
fun AlbumItem(
    id: String,
    name: String,
    artist: String,
    recordType: String,
    img: String,
    onClick: (id: String) -> Unit
) {
    Card(modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()
        .clickable { onClick(id) }) {
        Row(modifier = Modifier.padding(12.dp)) {
            AsyncImage(model = img, contentDescription = stringResource(R.string.album_cover))
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = artist, fontSize = 12.sp)
                Text(text = recordType, fontSize = 12.sp)
            }
        }
    }
}