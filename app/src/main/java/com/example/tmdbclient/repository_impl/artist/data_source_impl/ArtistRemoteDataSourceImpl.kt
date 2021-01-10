package com.example.tmdbclient.repository_impl.artist.data_source_impl

import com.example.tmdbclient.models.artists.PopularArtistsResponse
import com.example.tmdbclient.network.ApiInterface
import com.example.tmdbclient.repository_impl.artist.data_source.ArtistRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(
    private val apiInterface: ApiInterface,
    private val apiKey: String
) : ArtistRemoteDataSource {
    override suspend fun getArtists(): Response<PopularArtistsResponse> =
        apiInterface.getPopularArtists(apiKey)
}