package com.example.tmdbclient.repository_impl.tv_show

import com.example.tmdbclient.domain.repository.TvShowRepository
import com.example.tmdbclient.models.tv_shows.TvShow
import com.example.tmdbclient.repository_impl.tv_show.data_source.TvShowCacheDataSource
import com.example.tmdbclient.repository_impl.tv_show.data_source.TvShowLocalDataSource
import com.example.tmdbclient.repository_impl.tv_show.data_source.TvShowRemoteDataSource


class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
) : TvShowRepository {
    override suspend fun getTvShows(): List<TvShow>? {
        return getTvShowsFromCache()
    }

    override suspend fun updateTvShows(): List<TvShow>? {
        val updatedTvShowList = getTvShowsFromApi()
        tvShowLocalDataSource.clearAllData()
        tvShowLocalDataSource.saveTvShowsToDb(updatedTvShowList)
        tvShowCacheDataSource.saveTvShowToCache(updatedTvShowList)
        return updatedTvShowList
    }

    private suspend fun getTvShowsFromApi(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>

        try {
            val response = tvShowRemoteDataSource.getTvShows()
            val body = response.body()

            if (body != null) {
                tvShowList = body.tvShows
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return tvShowList
    }

    private suspend fun getTvShowsFromDb(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>

        try {
            tvShowList = tvShowLocalDataSource.getTvShowsFromDb()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (tvShowList.isNotEmpty()) {
            return tvShowList
        } else {
            tvShowList = getTvShowsFromApi()
            tvShowLocalDataSource.saveTvShowsToDb(tvShowList)
        }

        return tvShowList
    }

    private suspend fun getTvShowsFromCache(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>

        try {
            tvShowList = tvShowCacheDataSource.getTvShowsFromCache()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (tvShowList.isNotEmpty()) {
            return tvShowList
        } else {
            tvShowList = getTvShowsFromDb()
            tvShowCacheDataSource.saveTvShowToCache(tvShowList)
        }

        return tvShowList
    }
}