package com.example.tmdbclient.repository_impl.tv_show.data_source

import com.example.tmdbclient.models.tv_shows.PopularTvShowsResponse
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTvShows(): Response<PopularTvShowsResponse>
}