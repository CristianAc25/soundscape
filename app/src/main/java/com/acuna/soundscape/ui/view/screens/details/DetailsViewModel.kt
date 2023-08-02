package com.acuna.soundscape.ui.view.screens.details

import androidx.lifecycle.viewModelScope
import com.acuna.soundscape.data.AlbumRepository
import com.acuna.soundscape.domain.model.AlbumDetailsUiState
import com.acuna.soundscape.ui.view.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: AlbumRepository
): BaseViewModel<Event>() {

    private val _albumDetailsUiState: MutableStateFlow<AlbumDetailsUiState> = MutableStateFlow(AlbumDetailsUiState
        .Loading)
    val albumDetailsUiState: StateFlow<AlbumDetailsUiState>
        get() = _albumDetailsUiState

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.getAlbumsDetailsById -> getAlbumsDetailsById(event.id)
        }
    }

    private fun getAlbumsDetailsById(id: String) {
        viewModelScope.launch {
            if (id.isNotBlank()) {
                _albumDetailsUiState.value = AlbumDetailsUiState.Loading
                repository.getAlbumDetailsById(id).collect {
                    _albumDetailsUiState.value = it
                }
            }
        }
    }
}