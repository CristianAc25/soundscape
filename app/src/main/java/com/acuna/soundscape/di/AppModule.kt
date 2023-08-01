package com.acuna.soundscape.di

import com.acuna.soundscape.data.remote.services.AlbumService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesAlbumService(): AlbumService = AlbumService.create()
}