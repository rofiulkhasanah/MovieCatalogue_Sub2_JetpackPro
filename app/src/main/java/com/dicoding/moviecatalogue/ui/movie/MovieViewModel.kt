package com.dicoding.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalogue.data.DataRepository
import com.dicoding.moviecatalogue.data.source.local.MovieEntity

class MovieViewModel(private val dataRepository: DataRepository) : ViewModel() {
    fun getMovie(): LiveData<List<MovieEntity>> = dataRepository.getAllMovies()
}