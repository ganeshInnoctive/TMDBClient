package com.example.tmdbclient.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.FragmentCommonBinding
import com.example.tmdbclient.di.Injector
import com.example.tmdbclient.viewmodels.movie.MovieViewModel
import com.example.tmdbclient.viewmodels.movie.MovieViewModelFactory
import com.example.tmdbclient.views.activities.MainActivity.Companion.switchToNightMode
import com.example.tmdbclient.views.adapters.PopularMoviesAdapter
import javax.inject.Inject

class PopularMoviesFragment : Fragment(), View.OnClickListener {

    private lateinit var binder: FragmentCommonBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter

    @Inject
    lateinit var factory: MovieViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binder =
            DataBindingUtil.inflate(inflater, R.layout.fragment_common, container, false)

        initViews()
        initViewModel()
        initRecyclerView()

        return binder.root
    }

    private fun initViews() {
        binder.textViewFeatureTitle.text = "Popular Movies"
        binder.fabRefreshCommon.setOnClickListener(this)
        binder.imageViewOverflow.setOnClickListener(this)
    }

    private fun initViewModel() {
        (activity?.application as Injector).createMovieSubComponent().inject(this)
        movieViewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)
    }

    private fun initRecyclerView() {
        binder.recyclerViewCommon.layoutManager = LinearLayoutManager(activity)
        popularMoviesAdapter = PopularMoviesAdapter()
        binder.recyclerViewCommon.adapter = popularMoviesAdapter
        displayPopularMovies()
    }

    private fun displayPopularMovies() {
        binder.progressBarCommon.visibility = VISIBLE
        val responseLiveData = movieViewModel.getMovies()
        responseLiveData.observe(viewLifecycleOwner, {
            binder.progressBarCommon.visibility = GONE
            if (it != null) {
                popularMoviesAdapter.setList(it)
                popularMoviesAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun updatePopularMovies() {
        binder.progressBarCommon.visibility = VISIBLE
        val responseLiveData = movieViewModel.updateMovieList()
        responseLiveData.observe(viewLifecycleOwner, {
            binder.progressBarCommon.visibility = GONE
            if (it != null) {
                popularMoviesAdapter.setList(it)
                popularMoviesAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fabRefreshCommon -> {
                updatePopularMovies()
            }

            R.id.imageViewOverflow -> {
                showOverflowMenu()
            }
        }
    }

    private fun showOverflowMenu() {
        PopupMenu(context, binder.imageViewOverflow).apply {
            menuInflater.inflate(R.menu.menu_overflow, menu)
            setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.changeTheme) {
                    switchToNightMode()
                }
                true
            }
        }.show()
    }

    /*private fun switchTheme() {
        val isNightMode =
            AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        activity?.
        activity?.recreate()
    }*/
}