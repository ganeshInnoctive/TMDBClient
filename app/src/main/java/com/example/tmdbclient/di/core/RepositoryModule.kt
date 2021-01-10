package com.example.tmdbclient.di.core

import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowRepository
import com.example.tmdbclient.repository_impl.artist.ArtistRepositoryImpl
import com.example.tmdbclient.repository_impl.artist.data_source.ArtistCacheDataSource
import com.example.tmdbclient.repository_impl.artist.data_source.ArtistLocalDataSource
import com.example.tmdbclient.repository_impl.artist.data_source.ArtistRemoteDataSource
import com.example.tmdbclient.repository_impl.movie.MovieRepositoryImpl
import com.example.tmdbclient.repository_impl.movie.data_source.MovieCacheDataSource
import com.example.tmdbclient.repository_impl.movie.data_source.MovieLocalDataSource
import com.example.tmdbclient.repository_impl.movie.data_source.MovieRemoteDataSource
import com.example.tmdbclient.repository_impl.tv_show.TvShowRepositoryImpl
import com.example.tmdbclient.repository_impl.tv_show.data_source.TvShowCacheDataSource
import com.example.tmdbclient.repository_impl.tv_show.data_source.TvShowLocalDataSource
import com.example.tmdbclient.repository_impl.tv_show.data_source.TvShowRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieCacheDataSource: MovieCacheDataSource,
        movieLocalDataSource: MovieLocalDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieRemoteDataSource, movieLocalDataSource, movieCacheDataSource
        )
    }

    @Provides
    @Singleton
    fun provideTvShowRepository(
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        tvShowCacheDataSource: TvShowCacheDataSource,
        tvShowLocalDataSource: TvShowLocalDataSource
    ): TvShowRepository {
        return TvShowRepositoryImpl(
            tvShowRemoteDataSource, tvShowLocalDataSource, tvShowCacheDataSource
        )
    }

    @Provides
    @Singleton
    fun provideArtistRepository(
        artistRemoteDataSource: ArtistRemoteDataSource,
        artistCacheDataSource: ArtistCacheDataSource,
        artistLocalDataSource: ArtistLocalDataSource
    ): ArtistRepository {
        return ArtistRepositoryImpl(
            artistRemoteDataSource, artistLocalDataSource, artistCacheDataSource
        )
    }
}