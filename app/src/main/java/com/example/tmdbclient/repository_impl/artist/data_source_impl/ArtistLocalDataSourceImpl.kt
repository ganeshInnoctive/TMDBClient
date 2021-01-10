package com.example.tmdbclient.repository_impl.artist.data_source_impl

import com.example.tmdbclient.dao.ArtistDao
import com.example.tmdbclient.models.artists.Artist
import com.example.tmdbclient.repository_impl.artist.data_source.ArtistLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistLocalDataSourceImpl(private val artistDao: ArtistDao) : ArtistLocalDataSource {
    override suspend fun getArtistsFromDb(): List<Artist> {
        return artistDao.getArtists()
    }

    override suspend fun saveArtistToDb(artists: List<Artist>) {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.saveArtists(artists)
        }
    }

    override suspend fun clearAllData() {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.deleteAllArtists()
        }
    }
}