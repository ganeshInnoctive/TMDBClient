package com.example.tmdbclient.repository_impl.artist

import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.models.artists.Artist
import com.example.tmdbclient.repository_impl.artist.data_source.ArtistCacheDataSource
import com.example.tmdbclient.repository_impl.artist.data_source.ArtistLocalDataSource
import com.example.tmdbclient.repository_impl.artist.data_source.ArtistRemoteDataSource

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource,
) : ArtistRepository {

    override suspend fun getArtists(): List<Artist> {
        return getArtistsFromCache()
    }

    override suspend fun updateArtists(): List<Artist> {
        val updatedArtistList = getArtistsFromApi()
        artistLocalDataSource.clearAllData()
        artistLocalDataSource.saveArtistToDb(updatedArtistList)
        artistCacheDataSource.saveArtistsToCache(updatedArtistList)
        return updatedArtistList
    }

    private suspend fun getArtistsFromApi(): List<Artist> {
        lateinit var artistList: List<Artist>

        try {
            val response = artistRemoteDataSource.getArtists()
            val body = response.body()

            if (body != null) {
                artistList = body.artists
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return artistList
    }

    private suspend fun getMoviesFromDb(): List<Artist> {
        lateinit var artistList: List<Artist>

        try {
            artistList = artistLocalDataSource.getArtistsFromDb()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (artistList.isNotEmpty()) {
            return artistList
        } else {
            artistList = getArtistsFromApi()
            artistLocalDataSource.saveArtistToDb(artistList)
        }

        return artistList
    }

    private suspend fun getArtistsFromCache(): List<Artist> {
        lateinit var artistList: List<Artist>

        try {
            artistList = artistCacheDataSource.getArtistsFromCache()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (artistList.isNotEmpty()) {
            return artistList
        } else {
            artistList = getMoviesFromDb()
            artistCacheDataSource.saveArtistsToCache(artistList)
        }

        return artistList
    }
}