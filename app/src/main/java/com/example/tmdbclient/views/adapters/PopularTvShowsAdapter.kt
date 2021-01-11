package com.example.tmdbclient.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ItemRowCommonBinding
import com.example.tmdbclient.models.tv_shows.TvShow

class PopularTvShowsAdapter() : RecyclerView.Adapter<PopularTvShowsAdapter.ViewHolder>() {

    private val tvShowList = ArrayList<TvShow>()

    fun setList(tvShows: List<TvShow>) {
        tvShowList.clear()
        tvShowList.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : PopularTvShowsAdapter.ViewHolder {
        val binder: ItemRowCommonBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_row_common, parent, false
        )
        return ViewHolder(binder)
    }

    override fun onBindViewHolder(holder: PopularTvShowsAdapter.ViewHolder, position: Int) {
        holder.bindValuesToViews(tvShowList[position])
    }

    override fun getItemCount(): Int {
        return tvShowList.size
    }

    class ViewHolder(private val binder: ItemRowCommonBinding) :
        RecyclerView.ViewHolder(binder.root) {
        fun bindValuesToViews(tvShow: TvShow) {
            binder.textViewTitle.text = tvShow.name
            binder.textViewDescription.text = tvShow.overview

            val imageUrl = "https://image.tmdb.org/t/p/w500/${tvShow.backdropPath}"
            Glide.with(binder.imageViewPoster.context)
                .load(imageUrl)
                .into(binder.imageViewPoster)
        }
    }
}