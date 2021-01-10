package com.example.tmdbclient.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.FragmentPopularTvShowsBinding

class PopularTvShowsFragment : Fragment() {

    private lateinit var binder: FragmentPopularTvShowsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binder =
            DataBindingUtil.inflate(inflater, R.layout.fragment_popular_tv_shows, container, false)
        return binder.root
    }
}