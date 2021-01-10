package com.example.tmdbclient.models.tv_shows


import com.google.gson.annotations.SerializedName

data class PopularTvShowsResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val tvShows: List<TvShow>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)