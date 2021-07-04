package com.dicoding.moviecatalogue.ui.detail.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.moviecatalogue.R
import com.dicoding.moviecatalogue.data.source.local.MovieEntity
import com.dicoding.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.dicoding.moviecatalogue.databinding.MovieDetailBinding
import com.dicoding.moviecatalogue.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var detailBinding: MovieDetailBinding
    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        detailBinding = activityDetailMovieBinding.detailContent

        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(activityDetailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)
                viewModel.getMovie().observe(this, { movie -> populateMovie(movie) })
            }
        }
    }

    private fun populateMovie(movie: MovieEntity) {
        detailBinding.apply {
            tvMovieTitle.text = movie.title
            tvMovieDescription.text = movie.overview
            tvMovieDate.text = movie.release_date
            tvMovieRating.text = resources.getString(R.string.rating, movie.vote_average)
//            textRating.text = movie.vote_average
            Glide.with(this@DetailMovieActivity)
                    .load(movie.baseURL.plus(movie.poster_path))
                    .transform(RoundedCorners(20))
                    .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                    .error(R.drawable.ic_error)
                    )
                    .into(imgMovieImage)
        }
    }
}