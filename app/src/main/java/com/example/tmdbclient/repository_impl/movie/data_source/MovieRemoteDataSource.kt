package com.example.tmdbclient.repository_impl.movie.data_source

import com.example.tmdbclient.models.movies.PopularMoviesResponse
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovies() : Response<PopularMoviesResponse>
}