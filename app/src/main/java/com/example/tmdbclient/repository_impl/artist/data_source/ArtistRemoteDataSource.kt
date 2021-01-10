package com.example.tmdbclient.repository_impl.artist.data_source

import com.example.tmdbclient.models.artists.PopularArtistsResponse
import retrofit2.Response

interface ArtistRemoteDataSource {
    suspend fun getArtists(): Response<PopularArtistsResponse>
}