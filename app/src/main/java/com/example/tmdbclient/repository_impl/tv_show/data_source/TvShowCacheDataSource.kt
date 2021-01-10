package com.example.tmdbclient.repository_impl.tv_show.data_source

import com.example.tmdbclient.models.movies.Movie
import com.example.tmdbclient.models.tv_shows.TvShow

interface TvShowCacheDataSource {
    suspend fun getTvShowsFromCache(): List<TvShow>
    suspend fun saveTvShowToCache(tvShows: List<TvShow>)
}