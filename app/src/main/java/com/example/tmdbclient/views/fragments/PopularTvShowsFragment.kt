package com.example.tmdbclient.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.FragmentCommonBinding
import com.example.tmdbclient.di.Injector
import com.example.tmdbclient.viewmodels.tv_shows.TvShowViewModel
import com.example.tmdbclient.viewmodels.tv_shows.TvShowViewModelFactory
import com.example.tmdbclient.views.adapters.PopularTvShowsAdapter
import javax.inject.Inject

class PopularTvShowsFragment : Fragment(), View.OnClickListener {

    private lateinit var binder: FragmentCommonBinding
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var popularTvShowsAdapter: PopularTvShowsAdapter

    @Inject
    lateinit var factory: TvShowViewModelFactory

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
        binder.textViewFeatureTitle.text = "Popular TV Shows"
        binder.fabRefreshCommon.setOnClickListener(this)
        binder.imageViewOverflow.setOnClickListener(this)
    }

    private fun initViewModel() {
        (activity?.application as Injector).createTvShowSubComponent().inject(this)
        tvShowViewModel = ViewModelProvider(this, factory).get(TvShowViewModel::class.java)
    }

    private fun initRecyclerView() {
        binder.recyclerViewCommon.layoutManager = LinearLayoutManager(activity)
        popularTvShowsAdapter = PopularTvShowsAdapter()
        binder.recyclerViewCommon.adapter = popularTvShowsAdapter
        displayPopularTvShows()
    }

    private fun displayPopularTvShows() {
        binder.progressBarCommon.visibility = View.VISIBLE
        val responseLiveData = tvShowViewModel.getTvShows()
        responseLiveData.observe(viewLifecycleOwner, {
            binder.progressBarCommon.visibility = View.GONE
            if (it != null) {
                popularTvShowsAdapter.setList(it)
                popularTvShowsAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun updatePopularTvShows() {
        binder.progressBarCommon.visibility = View.VISIBLE
        val responseLiveData = tvShowViewModel.updateTvShowList()
        responseLiveData.observe(viewLifecycleOwner, {
            binder.progressBarCommon.visibility = View.GONE
            if (it != null) {
                popularTvShowsAdapter.setList(it)
                popularTvShowsAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fabRefreshCommon -> {
                updatePopularTvShows()
            }

            R.id.imageViewOverflow -> {

            }
        }
    }
}