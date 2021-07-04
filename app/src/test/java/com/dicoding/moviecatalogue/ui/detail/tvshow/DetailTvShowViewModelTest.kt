package com.dicoding.moviecatalogue.ui.detail.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.moviecatalogue.data.DataRepository
import com.dicoding.moviecatalogue.data.source.local.TvShowEntity
import com.dicoding.moviecatalogue.utils.DataTMDB
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {
    private lateinit var viewModel: DetailTvShowViewModel
    private val data = DataTMDB.generateTvShow()[0]
    private val tvShowId = data.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var tvShowObserver: Observer<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(dataRepository)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = data

        `when`(dataRepository.getTvShowById(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTvShow().value as TvShowEntity
        verify(dataRepository).getTvShowById(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(data.id, tvShowEntity.id)
        assertEquals(data.name, tvShowEntity.name)
        assertEquals(data.poster_path, tvShowEntity.poster_path)
        assertEquals(data.vote_average, tvShowEntity.vote_average)
        assertEquals(data.overview, tvShowEntity.overview)
        assertEquals(data.baseURL, tvShowEntity.baseURL)

        viewModel.getTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(data)
    }
}