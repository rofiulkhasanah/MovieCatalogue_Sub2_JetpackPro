package com.dicoding.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalogue.data.DataRepository
import com.dicoding.moviecatalogue.data.source.local.TvShowEntity

class TvShowViewModel(private val dataRepository: DataRepository) : ViewModel() {
    fun getTVShow(): LiveData<List<TvShowEntity>> = dataRepository.getAllTvSHow()
}