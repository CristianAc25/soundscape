package com.acuna.soundscape.ui.view.screens.albumList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.acuna.soundscape.R
import com.acuna.soundscape.ui.view.widgets.ConnectivityStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
@Composable
fun AlbumListScreen(
    viewModel: AlbumListViewModel,
    albumListViewModel: AlbumListViewModel = hiltViewModel(),
    onClick: (id: String) -> Unit
) {

    val albums = viewModel.albumPagingFlow.collectAsLazyPagingItems()

    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val items = remember { mutableStateListOf<String>() }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold (
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    actionColor = Color.Green,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = Color.White,
                    snackbarData = data
                )
            }
        },
        topBar = {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = text,
            onQueryChange = { text = it },
            onSearch = {
                if (text.isNotBlank()) {
                    items.add(text)
                    albumListViewModel.setEvent(Event.SearchAlbumsByQuery(text))
                    albums.refresh()
                }
                active = false
                text = ""
            },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text(text = stringResource(R.string.search),style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Light, fontSize = 18.sp) },
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
                        albumListViewModel.setEvent(Event.SearchAlbumsByQuery(it))
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
        ConnectivityStatus(scope, snackbarHostState, LocalContext.current)
        Box(modifier = Modifier
            .padding(paddingValue)
            .padding(top = 18.dp)) {
            if (albums.itemCount > 0) {
                AlbumList(
                    albumList = albums,
                    onClick = onClick
                )
            } else {
                NoResultsFoundView()
            }

        }
    }
}

@Composable
fun NoResultsFoundView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.no_results_found),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraLight
        )
    }
}


