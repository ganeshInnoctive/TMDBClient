package com.example.tmdbclient.repository_impl.movie.data_source_impl

import com.example.tmdbclient.models.movies.PopularMoviesResponse
import com.example.tmdbclient.network.ApiInterface
import com.example.tmdbclient.repository_impl.movie.data_source.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val apiInterface: ApiInterface,
    private val apiKey: String
) : MovieRemoteDataSource {
    override suspend fun getMovies(): Response<PopularMoviesResponse> =
        apiInterface.getPopularMovies(apiKey)
}