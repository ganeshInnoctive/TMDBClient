package com.example.tmdbclient.di.core

import com.example.tmdbclient.dao.ArtistDao
import com.example.tmdbclient.dao.MovieDao
import com.example.tmdbclient.dao.TvShowDao
import com.example.tmdbclient.repository_impl.artist.data_source.ArtistLocalDataSource
import com.example.tmdbclient.repository_impl.artist.data_source_impl.ArtistLocalDataSourceImpl
import com.example.tmdbclient.repository_impl.movie.data_source.MovieLocalDataSource
import com.example.tmdbclient.repository_impl.movie.data_source_impl.MovieLocalDataSourceImpl
import com.example.tmdbclient.repository_impl.tv_show.data_source.TvShowLocalDataSource
import com.example.tmdbclient.repository_impl.tv_show.data_source_impl.TvShowLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {
    @Provides
    @Singleton
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDao)
    }

    @Provides
    @Singleton
    fun provideTvShowLocalDataSource(tvShowDao: TvShowDao): TvShowLocalDataSource {
        return TvShowLocalDataSourceImpl(tvShowDao)
    }

    @Provides
    @Singleton
    fun provideArtistLocalDataSource(artistDao: ArtistDao): ArtistLocalDataSource {
        return ArtistLocalDataSourceImpl(artistDao)
    }
}