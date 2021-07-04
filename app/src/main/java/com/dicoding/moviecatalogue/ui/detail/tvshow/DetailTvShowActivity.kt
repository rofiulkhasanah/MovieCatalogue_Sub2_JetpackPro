package com.dicoding.moviecatalogue.ui.detail.tvshow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.moviecatalogue.R
import com.dicoding.moviecatalogue.data.source.local.TvShowEntity
import com.dicoding.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.dicoding.moviecatalogue.databinding.TvShowDetailBinding
import com.dicoding.moviecatalogue.viewmodel.ViewModelFactory

class DetailTvShowActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TVSHOW = "extra_movie"
    }

    private lateinit var detailBinding: TvShowDetailBinding
    private lateinit var activityDetailTvShowBinding: ActivityDetailTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        detailBinding = activityDetailTvShowBinding.detailTvshow

        setContentView(activityDetailTvShowBinding.root)

        setSupportActionBar(activityDetailTvShowBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailTvShowViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getString(DetailTvShowActivity.EXTRA_TVSHOW)
            if (tvShowId != null) {
                viewModel.setSelectedTvShow(tvShowId)
                viewModel.getTvShow().observe(this, { tvShow -> populateTVShow(tvShow) })
            }
        }
    }


    private fun populateTVShow(tvShow: TvShowEntity) {
        detailBinding.apply {
            tvTvShowTitle.text = tvShow.name
            tvTvShowDescription.text = tvShow.overview
            tvTvShowRating.text = resources.getString(R.string.rating, tvShow.vote_average)

            Glide.with(this@DetailTvShowActivity)
                    .load(tvShow.baseURL.plus(tvShow.poster_path))
                    .transform(RoundedCorners(20))
                    .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                    .error(R.drawable.ic_error)
                    )
                    .into(imgTvShowImage)
        }
    }
}