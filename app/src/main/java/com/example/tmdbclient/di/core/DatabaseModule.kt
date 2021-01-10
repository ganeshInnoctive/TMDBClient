package com.example.tmdbclient.di.core

import android.content.Context
import androidx.room.Room
import com.example.tmdbclient.dao.ArtistDao
import com.example.tmdbclient.dao.MovieDao
import com.example.tmdbclient.dao.TvShowDao
import com.example.tmdbclient.db.TmdbDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(context: Context): TmdbDatabase {
        return Room.databaseBuilder(context, TmdbDatabase::class.java, "tmdbclient")
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(tmdbDatabase: TmdbDatabase): MovieDao {
        return tmdbDatabase.movieDao()
    }

    @Provides
    @Singleton
    fun provideTvShowDao(tmdbDatabase: TmdbDatabase): TvShowDao {
        return tmdbDatabase.tvShowDao()
    }

    @Provides
    @Singleton
    fun provideArtistDao(tmdbDatabase: TmdbDatabase): ArtistDao {
        return tmdbDatabase.artistDao()
    }
}