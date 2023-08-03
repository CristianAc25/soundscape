package com.acuna.soundscape.data

import com.acuna.soundscape.data.local.AlbumDatabase
import com.acuna.soundscape.data.remote.services.AlbumService
import com.acuna.soundscape.domain.model.AlbumDetailsUiState
import com.acuna.soundscape.domain.model.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val albumService: AlbumService,
    val albumApi: AlbumService,
    val albumsDatabase: AlbumDatabase
) {

    fun getAlbumDetailsById(id: String): Flow<AlbumDetailsUiState> = flow {
        try {
            emit(toUiModel(albumService.getAlbumDetailsById(id)))
        } catch (throwable: Throwable) {
            emit(AlbumDetailsUiState.Error)
        }
    }
}