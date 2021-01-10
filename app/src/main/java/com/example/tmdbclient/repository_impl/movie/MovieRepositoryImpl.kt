package com.example.tmdbclient.repository_impl.movie

import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.models.movies.Movie
import com.example.tmdbclient.repository_impl.movie.data_source.MovieCacheDataSource
import com.example.tmdbclient.repository_impl.movie.data_source.MovieLocalDataSource
import com.example.tmdbclient.repository_impl.movie.data_source.MovieRemoteDataSource

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {
    override suspend fun getMovies(): List<Movie>? {
        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {
        val updatedMovieList = getMoviesFromApi()
        movieLocalDataSource.clearAllData()
        movieLocalDataSource.saveMovieToDb(updatedMovieList)
        movieCacheDataSource.saveMovieToCache(updatedMovieList)
        return updatedMovieList
    }

    private suspend fun getMoviesFromApi(): List<Movie> {
        lateinit var movieList: List<Movie>

        try {
            val response = movieRemoteDataSource.getMovies()
            val body = response.body()

            if (body != null) {
                movieList = body.movies
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return movieList
    }

    private suspend fun getMoviesFromDb(): List<Movie> {
        lateinit var movieList: List<Movie>

        try {
            movieList = movieLocalDataSource.getMoviesFromDb()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (movieList.isNotEmpty()) {
            return movieList
        } else {
            movieList = getMoviesFromApi()
            movieLocalDataSource.saveMovieToDb(movieList)
        }

        return movieList
    }

    private suspend fun getMoviesFromCache(): List<Movie> {
        lateinit var movieList: List<Movie>

        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (movieList.isNotEmpty()) {
            return movieList
        } else {
            movieList = getMoviesFromDb()
            movieCacheDataSource.saveMovieToCache(movieList)
        }

        return movieList
    }
}