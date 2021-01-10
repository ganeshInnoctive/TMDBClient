package com.example.tmdbclient.di.artist

import com.example.tmdbclient.views.fragments.PopularArtistsFragment
import dagger.Subcomponent

@ArtistScope
@Subcomponent(modules = [ArtistModule::class])
interface ArtistSubComponent {
    fun inject(artistsFragment: PopularArtistsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ArtistSubComponent
    }
}