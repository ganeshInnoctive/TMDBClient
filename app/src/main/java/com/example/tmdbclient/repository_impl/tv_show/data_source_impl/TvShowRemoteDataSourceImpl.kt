package com.example.tmdbclient.repository_impl.tv_show.data_source_impl


import com.example.tmdbclient.models.tv_shows.PopularTvShowsResponse
import com.example.tmdbclient.network.ApiInterface
import com.example.tmdbclient.repository_impl.tv_show.data_source.TvShowRemoteDataSource

import retrofit2.Response

class TvShowRemoteDataSourceImpl(
    private val apiInterface: ApiInterface,
    private val apiKey: String
) : TvShowRemoteDataSource {
    override suspend fun getTvShows(): Response<PopularTvShowsResponse> =
        apiInterface.getPopularTvShows(apiKey)
}