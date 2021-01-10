package com.example.tmdbclient.repository_impl.movie.data_source_impl

import com.example.tmdbclient.models.movies.Movie
import com.example.tmdbclient.repository_impl.movie.data_source.MovieCacheDataSource

class MovieCacheDataSourceImpl : MovieCacheDataSource {
    private var movieList = ArrayList<Movie>()
    override suspend fun getMoviesFromCache(): List<Movie> {
        return movieList
    }

    override suspend fun saveMovieToCache(movies: List<Movie>) {
        movieList.clear()
        movieList = ArrayList(movies)
    }
}