package com.example.tmdbclient.repository_impl.artist.data_source

import com.example.tmdbclient.models.artists.Artist

interface ArtistLocalDataSource {
    suspend fun getArtistsFromDb(): List<Artist>
    suspend fun saveArtistToDb(artists: List<Artist>)
    suspend fun clearAllData()
}