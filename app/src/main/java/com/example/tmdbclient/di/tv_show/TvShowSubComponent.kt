package com.example.tmdbclient.di.tv_show

import com.example.tmdbclient.views.fragments.PopularTvShowsFragment
import dagger.Subcomponent

@TvShowScope
@Subcomponent(modules = [TvShowModule::class])
interface TvShowSubComponent {
    fun inject(tvShowsFragment: PopularTvShowsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): TvShowSubComponent
    }
}