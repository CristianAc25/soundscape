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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.acuna.soundscape.R
import com.acuna.soundscape.domain.model.AlbumDTO
import com.acuna.soundscape.ui.view.widgets.ColoredPill

@Composable
fun AlbumList(
    albumList: LazyPagingItems<AlbumDTO>,
    onClick: (id: String) -> Unit
) {

    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        LazyColumn() {
            items(
                count = albumList.itemCount,
                // Here we use the new itemKey extension on LazyPagingItems to
                // handle placeholders automatically, ensuring you only need to provide
                // keys for real items
                key = albumList.itemKey { it.id },
                // Similarly, itemContentType lets you set a custom content type for each item
                contentType = albumList.itemContentType { "contentType" }
            ) { index ->
                // As the standard items call provides only the index, we get the item
                // directly from our lazyPagingItems
                val item = albumList[index]
                if(item != null) {
                    AlbumItem(
                        id = item.id,
                        name = item.title,
                        artist = item.artist,
                        recordType = item.recordType,
                        img = item.img,
                        onClick = onClick
                    )
                }
            }

            /*items(count = albumList.size, itemContent = { index ->
                AlbumItem(
                    id = albumList[index].id,
                    name = albumList[index].title,
                    artist = albumList[index].artist,
                    recordType = albumList[index].recordType,
                    img = albumList[index].img,
                    onClick = onClick
                )
            })*/
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
        Row() {
            AsyncImage(model = img, contentDescription = stringResource(R.string.album_cover))
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = name, style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Light, fontSize = 18.sp)
                Text(text = artist, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(18.dp))
                ColoredPill(text = recordType, color = MaterialTheme.colorScheme.onBackground)
            }
        }
    }
}