package com.sheyla.mymovies.ui.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sheyla.mymovies.R
import com.sheyla.mymovies.data.base.Constants
import com.sheyla.mymovies.domain.Movie
import com.sheyla.mymovies.onclick.MovieListener

class MoviesAdapter(
    val context: Context,
    var dataSet: MutableList<Movie> = mutableListOf()
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageMovie: ImageView? = view.findViewById(R.id.imageView5)
        var movieTitle: TextView? = view.findViewById(R.id.title_movie)
        var movieId: TextView? = view.findViewById(R.id.movie_id)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recycler_movies_list, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(dataSet[position].imgHome !== ""){
            holder.imageMovie?.let { Glide.with(context).load(Constants.BASE_URL_IMAGE.value + dataSet[position].imgHome).into(it) }
        }
        holder.movieTitle?.text = dataSet[position].title

        holder.movieId?.text = dataSet[position].id.toString()
    }

    override fun getItemCount() = dataSet.size

}

