package com.acuna.soundscape.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acuna.soundscape.data.local.entities.search.AlbumEntity

@Database(
    entities = [AlbumEntity::class],
    version = 1
)
abstract class AlbumDatabase: RoomDatabase() {

    abstract val dao: AlbumDao
}