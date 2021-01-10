package com.example.tmdbclient.repository_impl.artist.data_source

import com.example.tmdbclient.models.artists.Artist

interface ArtistCacheDataSource {
    suspend fun getArtistsFromCache(): List<Artist>
    suspend fun saveArtistsToCache(artists: List<Artist>)
}