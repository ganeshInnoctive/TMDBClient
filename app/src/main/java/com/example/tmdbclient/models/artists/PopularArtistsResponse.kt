package com.example.tmdbclient.models.artists


import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class PopularArtistsResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val artists: List<Artist>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)