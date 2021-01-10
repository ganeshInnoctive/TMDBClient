package com.example.tmdbclient.di.movie

import com.example.tmdbclient.views.fragments.PopularArtistsFragment
import com.example.tmdbclient.views.fragments.PopularMoviesFragment
import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent {
    fun inject(moviesFragment: PopularMoviesFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieSubComponent
    }
}