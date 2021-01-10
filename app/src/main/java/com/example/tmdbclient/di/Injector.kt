package com.example.tmdbclient.di

import com.example.tmdbclient.di.artist.ArtistSubComponent
import com.example.tmdbclient.di.movie.MovieSubComponent
import com.example.tmdbclient.di.tv_show.TvShowSubComponent

interface Injector {
    fun createMovieSubComponent(): MovieSubComponent
    fun createTvShowSubComponent(): TvShowSubComponent
    fun createArtistSubComponent(): ArtistSubComponent
}