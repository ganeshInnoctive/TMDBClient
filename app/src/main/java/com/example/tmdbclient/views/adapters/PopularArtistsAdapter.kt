package com.example.tmdbclient.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ItemRowCommonBinding
import com.example.tmdbclient.models.artists.Artist

class PopularArtistsAdapter() : RecyclerView.Adapter<PopularArtistsAdapter.ViewHolder>() {

    private val artistList = ArrayList<Artist>()

    fun setList(artists: List<Artist>) {
        artistList.clear()
        artistList.addAll(artists)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : PopularArtistsAdapter.ViewHolder {
        val binder: ItemRowCommonBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_row_common, parent, false
        )
        return ViewHolder(binder)
    }

    override fun onBindViewHolder(holder: PopularArtistsAdapter.ViewHolder, position: Int) {
        holder.bindValuesToViews(artistList[position])
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    class ViewHolder(private val binder: ItemRowCommonBinding) :
        RecyclerView.ViewHolder(binder.root) {
        fun bindValuesToViews(artist: Artist) {
            binder.textViewTitle.text = artist.name
            binder.textViewDescription.text = artist.popularity.toString()

            val imageUrl = "https://image.tmdb.org/t/p/w500/${artist.profilePath}"
            Glide.with(binder.imageViewPoster.context)
                .load(imageUrl)
                .into(binder.imageViewPoster)
        }
    }
}