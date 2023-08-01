package com.acuna.soundscape.domain.model

sealed class AlbumUiState {
    data class Success(val albumUiStateModelList: List<AlbumUiModel>) : AlbumUiState()
    object Loading : AlbumUiState()
    object Error : AlbumUiState()
}

data class AlbumUiModel(
    val id: String,
    val title: String,
    val artist: String,
    val recordType: String,
    val img: String
)
