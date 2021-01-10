package com.example.tmdbclient.di.core

import com.example.tmdbclient.repository_impl.artist.data_source.ArtistCacheDataSource
import com.example.tmdbclient.repository_impl.artist.data_source_impl.ArtistCacheDataSourceImpl
import com.example.tmdbclient.repository_impl.movie.data_source.MovieCacheDataSource
import com.example.tmdbclient.repository_impl.movie.data_source_impl.MovieCacheDataSourceImpl
import com.example.tmdbclient.repository_impl.tv_show.data_source.TvShowCacheDataSource
import com.example.tmdbclient.repository_impl.tv_show.data_source_impl.TvShowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Provides
    @Singleton
    fun provideMovieCacheDataSource(): MovieCacheDataSource {
        return MovieCacheDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideTvShowCacheDataSource(): TvShowCacheDataSource {
        return TvShowCacheDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideArtistCacheDataSource(): ArtistCacheDataSource {
        return ArtistCacheDataSourceImpl()
    }
}