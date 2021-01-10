package com.example.tmdbclient.domain.usecase

import com.example.tmdbclient.domain.repository.TvShowRepository
import com.example.tmdbclient.models.tv_shows.TvShow

class UpdateTvShowUseCase(private val tvShowRepository: TvShowRepository) {
    suspend fun execute(): List<TvShow>? = tvShowRepository.updateTvShows()
}