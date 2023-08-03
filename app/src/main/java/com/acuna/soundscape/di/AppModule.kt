package com.acuna.soundscape.di

import android.content.Context
import androidx.room.Room
import com.acuna.soundscape.data.local.AlbumDatabase
import com.acuna.soundscape.data.remote.services.AlbumService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAlbumDatabase(@ApplicationContext context: Context): AlbumDatabase {
        return Room.databaseBuilder(
            context,
            AlbumDatabase::class.java,
            "albums.db"
        ).build()
    }

    @Provides
    fun providesAlbumService(): AlbumService = AlbumService.create()

}