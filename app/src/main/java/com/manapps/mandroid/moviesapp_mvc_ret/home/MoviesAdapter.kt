package com.manapps.mandroid.moviesapp_mvc_ret.home

import android.content.Context
import android.text.SpannableString
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manapps.mandroid.moviesapp_mvc_ret.R
import com.manapps.mandroid.moviesapp_mvc_ret.databinding.MovieItemLayoutBinding
import com.manapps.mandroid.moviesapp_mvc_ret.models.Movies
import com.manapps.mandroid.moviesapp_mvc_ret.utils.Constants
import com.manapps.mandroid.moviesapp_mvc_ret.utils.DateFormatter
import com.manapps.mandroid.moviesapp_mvc_ret.utils.DownloadImage
import java.lang.Exception

class MoviesAdapter(private val context: Context,private val moviesList: List<Movies>) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(
            MovieItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) =
        holder.bind(moviesList[position], context)

    override fun getItemCount(): Int = moviesList.size
    class MoviesViewHolder(private var holderBinding: MovieItemLayoutBinding) :
        RecyclerView.ViewHolder(holderBinding.root) {
        fun bind(movie: Movies, context: Context) {
            try {

                holderBinding.movieTitleTv.text = movie.title
                holderBinding.releaseDateTv.text = DateFormatter.convertStringDate(
                    movie.releaseDate,
                    Constants.inputDateFormat,
                    Constants.outputDateFormat
                )
                var rating: Float? = movie.voteAverage?.toFloat() ?: 0f
                rating = rating!! / 2
                holderBinding.ratingBar.rating = rating
                val span = SpannableString(
                    context.resources.getString(
                        R.string.total_votes_count,
                        movie.voteCount.toString()
                    )
                )
                holderBinding.totalRatings.text = span
                DownloadImage.loadImage(holderBinding.moviePosterImgView, context, movie.posterPath)

            } catch (exception: Exception) {
                Log.d("Exception : ", exception.toString())
            }
        }
    }

}