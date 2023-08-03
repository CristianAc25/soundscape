package com.acuna.soundscape.data.remote.services

import com.acuna.soundscape.BuildConfig
import com.acuna.soundscape.data.remote.entities.details.AlbumDetails
import com.acuna.soundscape.data.remote.entities.search.AlbumSearch
import com.acuna.soundscape.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumService {

    @GET("search/album")
    suspend fun getAlbumsBySearchQuery(
        @Query("index") index: Int = 1,
        @Query("limit") limit: Int = 20,
        @Query("q") searchQuery: String
    ): AlbumSearch

    @GET("album/{id}")
    suspend fun getAlbumDetailsById(@Path("id") id: String): AlbumDetails

    companion object {
        fun create(): AlbumService {
            val logger = HttpLoggingInterceptor()
            logger.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC else
                HttpLoggingInterceptor.Level.NONE

            val client = OkHttpClient.Builder().addInterceptor(logger).build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AlbumService::class.java)
        }
    }
}