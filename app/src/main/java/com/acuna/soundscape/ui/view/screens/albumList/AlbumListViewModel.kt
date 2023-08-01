package com.acuna.soundscape.ui.view.screens.albumList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acuna.soundscape.data.AlbumRepository
import com.acuna.soundscape.domain.model.AlbumUiState
import com.acuna.soundscape.utils.Constants.DEFAULT_INTIAL_SEARCH
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val repository: AlbumRepository
): ViewModel() {

    private val _albumListUiState: MutableStateFlow<AlbumUiState> = MutableStateFlow(AlbumUiState.Loading)
    val albumListUiState: StateFlow<AlbumUiState>
        get() = _albumListUiState

    init {
        loadInitialAlbumData()
    }

    private fun loadInitialAlbumData() {
        getAlbumsBySearchQuery(DEFAULT_INTIAL_SEARCH)
    }

    fun getAlbumsBySearchQuery(searchQuery: String) {
        viewModelScope.launch {
            if (searchQuery.isNotBlank()) {
                _albumListUiState.value = AlbumUiState.Loading
                repository.getAlbumsBySearchQuery(searchQuery).collect {
                    _albumListUiState.value = it
                }
            }
        }
    }
}