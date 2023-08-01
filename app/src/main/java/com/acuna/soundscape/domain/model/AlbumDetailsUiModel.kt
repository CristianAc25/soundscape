package com.acuna.soundscape.domain.model

sealed class AlbumDetailsUiState {
    data class Success(val albumDetailsUiModel: AlbumDetailsUiModel) : AlbumDetailsUiState()
    object Loading : AlbumDetailsUiState()
    object Error : AlbumDetailsUiState()
}

data class AlbumDetailsUiModel(
    val id: String,
    val title: String,
    val artist: String,
    val recordType: String,
    val img: String,
    val releaseDate: String,
    val duration: Int,
    val genres: List<String>,
    val tracks: List<TrackUiModel>

)
