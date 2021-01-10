package com.example.tmdbclient.repository_impl.tv_show.data_source_impl

import com.example.tmdbclient.models.tv_shows.TvShow
import com.example.tmdbclient.repository_impl.tv_show.data_source.TvShowCacheDataSource


class TvShowCacheDataSourceImpl : TvShowCacheDataSource {
    private var tvShowList = ArrayList<TvShow>()
    override suspend fun getTvShowsFromCache(): List<TvShow> {
        return tvShowList
    }

    override suspend fun saveTvShowToCache(tvShows: List<TvShow>) {
        tvShowList.clear()
        tvShowList = ArrayList(tvShows)
    }
}