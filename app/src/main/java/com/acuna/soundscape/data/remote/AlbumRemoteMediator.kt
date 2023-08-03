package com.acuna.soundscape.data.remote

import androidx.compose.runtime.MutableState
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.acuna.soundscape.data.local.AlbumDatabase
import com.acuna.soundscape.data.local.entities.search.AlbumEntity
import com.acuna.soundscape.data.mappers.toAlbumRoomEntity
import com.acuna.soundscape.data.remote.services.AlbumService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class AlbumRemoteMediator(
    private val albumDb: AlbumDatabase,
    private val albumApi: AlbumService,
    private val searchQuery: MutableState<String>
): RemoteMediator<Int, AlbumEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AlbumEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id.toInt() / state.config.pageSize) + 1
                    }
                }
            }

            val albums = albumApi.getAlbumsBySearchQuery(
                index = loadKey,
                limit = state.config.pageSize,
                searchQuery = searchQuery.value
            )

            albumDb.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    albumDb.dao.clearAll()
                }
                val albumEntities = albums.data.map { it.toAlbumRoomEntity() }
                albumDb.dao.upsertAll(albumEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = albums.data.isEmpty()
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}