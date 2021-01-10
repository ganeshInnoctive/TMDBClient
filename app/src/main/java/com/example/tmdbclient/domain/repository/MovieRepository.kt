package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.models.movies.Movie

interface MovieRepository {
    suspend fun getMovies(): List<Movie>?
    suspend fun updateMovies(): List<Movie>?
}