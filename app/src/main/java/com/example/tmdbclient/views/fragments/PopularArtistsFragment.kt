package com.example.tmdbclient.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.FragmentCommonBinding
import com.example.tmdbclient.di.Injector
import com.example.tmdbclient.viewmodels.artist.ArtistViewModel
import com.example.tmdbclient.viewmodels.artist.ArtistViewModelFactory
import com.example.tmdbclient.views.activities.MainActivity
import com.example.tmdbclient.views.adapters.PopularArtistsAdapter
import javax.inject.Inject


class PopularArtistsFragment : Fragment(), View.OnClickListener {

    private lateinit var binder: FragmentCommonBinding
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var popularArtistsAdapter: PopularArtistsAdapter

    @Inject
    lateinit var factory: ArtistViewModelFactory

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
        binder.textViewFeatureTitle.text = "Popular Artists"
        binder.fabRefreshCommon.setOnClickListener(this)
        binder.imageViewOverflow.setOnClickListener(this)
    }

    private fun initViewModel() {
        (activity?.application as Injector).createArtistSubComponent().inject(this)
        artistViewModel = ViewModelProvider(this, factory).get(ArtistViewModel::class.java)
    }

    private fun initRecyclerView() {
        binder.recyclerViewCommon.layoutManager = LinearLayoutManager(activity)
        popularArtistsAdapter = PopularArtistsAdapter()
        binder.recyclerViewCommon.adapter = popularArtistsAdapter
        displayPopularArtists()
    }

    private fun displayPopularArtists() {
        binder.progressBarCommon.visibility = View.VISIBLE
        val responseLiveData = artistViewModel.getArtists()
        responseLiveData.observe(viewLifecycleOwner, {
            binder.progressBarCommon.visibility = View.GONE
            if (it != null) {
                popularArtistsAdapter.setList(it)
                popularArtistsAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun updatePopularArtists() {
        binder.progressBarCommon.visibility = View.VISIBLE
        val responseLiveData = artistViewModel.updateArtistList()
        responseLiveData.observe(viewLifecycleOwner, {
            binder.progressBarCommon.visibility = View.GONE
            if (it != null) {
                popularArtistsAdapter.setList(it)
                popularArtistsAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fabRefreshCommon -> {
                updatePopularArtists()
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
                    (activity as MainActivity).switchToNightMode()
                }
                true
            }
        }.show()
    }
}