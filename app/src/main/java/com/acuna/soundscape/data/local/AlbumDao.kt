package com.acuna.soundscape.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.acuna.soundscape.data.local.entities.search.AlbumEntity

@Dao
interface AlbumDao {

    @Upsert
    suspend fun upsertAll(albums: List<AlbumEntity>)

    @Query("SELECT * FROM albumentity")
    fun pagingSource(): PagingSource<Int, AlbumEntity>

    @Query("DELETE FROM albumentity")
    suspend fun clearAll()
}