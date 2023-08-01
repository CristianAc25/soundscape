package com.acuna.soundscape.ui.view.screens.albumList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.acuna.soundscape.R
import com.acuna.soundscape.domain.model.AlbumUiState
import com.acuna.soundscape.ui.view.Error
import com.acuna.soundscape.ui.view.Loading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumListScreen(
    albumListViewModel: AlbumListViewModel = hiltViewModel(),
    onClick: (id: String) -> Unit
) {
    val viewState by albumListViewModel.albumListUiState.collectAsStateWithLifecycle()

    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val items = remember { mutableStateListOf<String>() }

    Scaffold (topBar = {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = text,
            onQueryChange = { text = it },
            onSearch = {
                items.add(text)
                albumListViewModel.getAlbumsBySearchQuery(text)
                active = false
                text = ""
            },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text(text = stringResource(R.string.search)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.icon_search)
                )
            },
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable { text = "" },
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.icon_close)
                    )
                }
            }
        ) {
            items.forEach {
                Row(modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        albumListViewModel.getAlbumsBySearchQuery(it)
                        active = false
                    }
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        imageVector = Icons.Default.History,
                        contentDescription = stringResource(R.string.icon_history)
                    )
                    Text(text = it)
                }
            }
        }
    }) { paddingValue ->
        Box(modifier = Modifier.padding(paddingValue)) {
            MainContent(viewState, onClick)
        }
    }

}

@Composable
private fun MainContent(
    uiState: AlbumUiState,
    onClick: (id: String) -> Unit
) {
    when (uiState) {
        AlbumUiState.Error -> Error()
        AlbumUiState.Loading -> Loading()
        is AlbumUiState.Success -> AlbumList(
            albumList = uiState.albumUiStateModelList,
            onClick = onClick
        )
    }
}