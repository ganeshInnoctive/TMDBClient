package com.example.tmdbclient.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tmdbclient.dao.ArtistDao
import com.example.tmdbclient.dao.MovieDao
import com.example.tmdbclient.dao.TvShowDao
import com.example.tmdbclient.models.artists.Artist
import com.example.tmdbclient.models.movies.Movie
import com.example.tmdbclient.models.tv_shows.TvShow
import com.example.tmdbclient.utils.DataConverter

@Database(
    entities = [Movie::class, TvShow::class, Artist::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class TmdbDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun artistDao(): ArtistDao
}