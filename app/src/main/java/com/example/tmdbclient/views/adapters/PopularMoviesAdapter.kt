package com.example.tmdbclient.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ItemRowCommonBinding
import com.example.tmdbclient.models.movies.Movie

class PopularMoviesAdapter() : RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder>() {

    private val movieList = ArrayList<Movie>()

    fun setList(movies: List<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : PopularMoviesAdapter.ViewHolder {
        val binder: ItemRowCommonBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_row_common, parent, false
        )
        return ViewHolder(binder)
    }

    override fun onBindViewHolder(holder: PopularMoviesAdapter.ViewHolder, position: Int) {
        holder.bindValuesToViews(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(private val binder: ItemRowCommonBinding) :
        RecyclerView.ViewHolder(binder.root) {
        fun bindValuesToViews(movie: Movie) {
            binder.textViewTitle.text = movie.title
            binder.textViewDescription.text = movie.overview

            val imageUrl = "https://image.tmdb.org/t/p/w500/${movie.backdropPath}"
            Glide.with(binder.imageViewPoster.context)
                .load(imageUrl)
                .into(binder.imageViewPoster)
        }
    }
}