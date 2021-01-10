package com.example.tmdbclient.repository_impl.tv_show.data_source

import com.example.tmdbclient.models.tv_shows.TvShow

interface TvShowLocalDataSource {
    suspend fun getTvShowsFromDb(): List<TvShow>
    suspend fun saveTvShowsToDb(tvShows: List<TvShow>)
    suspend fun clearAllData()
}