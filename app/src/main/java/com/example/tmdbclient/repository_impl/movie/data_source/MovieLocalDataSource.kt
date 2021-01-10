package com.example.tmdbclient.repository_impl.movie.data_source

import com.example.tmdbclient.models.movies.Movie

interface MovieLocalDataSource {
    suspend fun getMoviesFromDb(): List<Movie>
    suspend fun saveMovieToDb(movies: List<Movie>)
    suspend fun clearAllData()
}