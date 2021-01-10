package com.example.tmdbclient.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.models.artists.Artist
import com.example.tmdbclient.models.movies.Movie
import com.example.tmdbclient.models.tv_shows.TvShow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie>)

    @Query("DELETE FROM tb_popular_movie")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM tb_popular_movie")
    suspend fun getMovies() : List<Movie>
}