package com.example.tmdbclient.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.FragmentPopularMoviesBinding
import com.example.tmdbclient.di.Injector
import com.example.tmdbclient.viewmodels.movie.MovieViewModel
import com.example.tmdbclient.viewmodels.movie.MovieViewModelFactory
import javax.inject.Inject

class PopularMoviesFragment : Fragment() {

    private lateinit var binder: FragmentPopularMoviesBinding
    private lateinit var movieViewModel: MovieViewModel

    @Inject
    lateinit var factory: MovieViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binder =
            DataBindingUtil.inflate(inflater, R.layout.fragment_popular_movies, container, false)

        (activity?.application as Injector).createMovieSubComponent().inject(this)
        movieViewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)

        val responseLiveData = movieViewModel.getMovies()
        responseLiveData.observe(viewLifecycleOwner, {
            Log.i("PopularMoviesFragment", "Movie Name : ${it?.get(0)?.title}")
        })

        return binder.root
    }

}