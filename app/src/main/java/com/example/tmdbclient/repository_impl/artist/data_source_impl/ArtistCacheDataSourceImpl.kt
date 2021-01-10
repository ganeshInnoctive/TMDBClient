package com.example.tmdbclient.repository_impl.artist.data_source_impl

import com.example.tmdbclient.models.artists.Artist
import com.example.tmdbclient.repository_impl.artist.data_source.ArtistCacheDataSource

class ArtistCacheDataSourceImpl : ArtistCacheDataSource {
    private var artistList = ArrayList<Artist>()
    override suspend fun getArtistsFromCache(): List<Artist> {
        return artistList
    }

    override suspend fun saveArtistsToCache(artists: List<Artist>) {
        artistList.clear()
        artistList = ArrayList(artists)
    }
}