package com.example.tmdbclient.repository_impl.movie.data_source

import com.example.tmdbclient.models.movies.Movie

interface MovieCacheDataSource {
    suspend fun getMoviesFromCache(): List<Movie>
    suspend fun saveMovieToCache(movies: List<Movie>)
}