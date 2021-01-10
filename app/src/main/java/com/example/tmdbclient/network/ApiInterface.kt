package com.example.tmdbclient.network

import com.example.tmdbclient.models.artists.PopularArtistsResponse
import com.example.tmdbclient.models.movies.PopularMoviesResponse
import com.example.tmdbclient.models.tv_shows.PopularTvShowsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<PopularMoviesResponse>

    @GET("tv/popular")
    suspend fun getPopularTvShows(@Query("api_key") apiKey: String): Response<PopularTvShowsResponse>

    @GET("person/popular")
    suspend fun getPopularArtists(@Query("api_key") apiKey: String): Response<PopularArtistsResponse>
}