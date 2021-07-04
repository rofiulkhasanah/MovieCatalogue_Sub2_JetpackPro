package com.dicoding.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.dicoding.moviecatalogue.data.source.local.MovieEntity
import com.dicoding.moviecatalogue.data.source.local.TvShowEntity

interface DataSource {
    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun getAllTvSHow(): LiveData<List<TvShowEntity>>

    fun getMovieById(movieId: String): LiveData<MovieEntity>

    fun getTvShowById(tvShowId: String): LiveData<TvShowEntity>
}