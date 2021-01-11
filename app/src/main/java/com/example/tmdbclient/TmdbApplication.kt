package com.example.tmdbclient

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.example.tmdbclient.di.Injector
import com.example.tmdbclient.di.artist.ArtistSubComponent
import com.example.tmdbclient.di.core.*
import com.example.tmdbclient.di.movie.MovieSubComponent
import com.example.tmdbclient.di.tv_show.TvShowSubComponent

class TmdbApplication : Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        val sharedPreferences = getSharedPreferences("TmdbPreferences", Context.MODE_PRIVATE)
        val savedMode = sharedPreferences.getInt("SAVED_NIGHT_MODE", 0)

        AppCompatDelegate.setDefaultNightMode(savedMode)

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY))
            .build()
    }

    override fun createMovieSubComponent(): MovieSubComponent {
        return appComponent.movieSubComponent().create()
    }

    override fun createTvShowSubComponent(): TvShowSubComponent {
        return appComponent.tvShowSubComponent().create()
    }

    override fun createArtistSubComponent(): ArtistSubComponent {
        return appComponent.artistSubComponent().create()
    }
}