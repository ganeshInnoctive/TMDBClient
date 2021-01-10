package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.models.tv_shows.TvShow

interface TvShowRepository {
    suspend fun getTvShows(): List<TvShow>?
    suspend fun updateTvShows(): List<TvShow>?
}