package com.acuna.soundscape.ui.view.screens.albumList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.acuna.soundscape.data.AlbumRepository
import com.acuna.soundscape.data.local.AlbumDatabase
import com.acuna.soundscape.data.remote.AlbumRemoteMediator
import com.acuna.soundscape.data.remote.services.AlbumService
import com.acuna.soundscape.domain.model.AlbumDTO
import com.acuna.soundscape.domain.model.toAlbumDto
import com.acuna.soundscape.ui.view.screens.BaseViewModel
import com.acuna.soundscape.utils.Constants.DEFAULT_INTIAL_SEARCH
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val repository: AlbumRepository,
    private val albumApi: AlbumService,
    private val albumsDatabase: AlbumDatabase
): BaseViewModel<Event>() {

    var query = mutableStateOf(DEFAULT_INTIAL_SEARCH)

    var albumPagingFlow: Flow<PagingData<AlbumDTO>> = Pager(
        config = PagingConfig(
            pageSize = 15,
            prefetchDistance = 10,
            initialLoadSize = 15,
        ),
        pagingSourceFactory = {
            albumsDatabase.dao.pagingSource()
        },
        remoteMediator = AlbumRemoteMediator(
            albumsDatabase,
            albumApi,
            searchQuery = query
        )
    ).flow.map { pagingData ->
        pagingData.map { it.toAlbumDto() }
    }.cachedIn(viewModelScope)

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.SearchAlbumsByQuery -> getAlbumsBySearchQuery(event.searchQuery)
        }
    }

    private fun getAlbumsBySearchQuery(searchQuery: String) {
        query.value = searchQuery
    }
}