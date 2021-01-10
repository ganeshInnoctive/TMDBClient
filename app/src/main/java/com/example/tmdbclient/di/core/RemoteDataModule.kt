package com.example.tmdbclient.di.core

import com.example.tmdbclient.network.ApiInterface
import com.example.tmdbclient.repository_impl.artist.data_source.ArtistRemoteDataSource
import com.example.tmdbclient.repository_impl.artist.data_source_impl.ArtistRemoteDataSourceImpl
import com.example.tmdbclient.repository_impl.movie.data_source.MovieRemoteDataSource
import com.example.tmdbclient.repository_impl.movie.data_source_impl.MovieRemoteDataSourceImpl
import com.example.tmdbclient.repository_impl.tv_show.data_source.TvShowRemoteDataSource
import com.example.tmdbclient.repository_impl.tv_show.data_source_impl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val apiKey: String) {

    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(apiInterface: ApiInterface): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(apiInterface, apiKey)
    }

    @Provides
    @Singleton
    fun provideTvShowRemoteDataSource(apiInterface: ApiInterface): TvShowRemoteDataSource {
        return TvShowRemoteDataSourceImpl(apiInterface, apiKey)
    }

    @Provides
    @Singleton
    fun provideArtistRemoteDataSource(apiInterface: ApiInterface): ArtistRemoteDataSource {
        return ArtistRemoteDataSourceImpl(apiInterface, apiKey)
    }
}