package com.dicoding.moviecatalogue.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.moviecatalogue.data.DataRepository
import com.dicoding.moviecatalogue.data.source.local.MovieEntity
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
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val data = DataTMDB.generateMovies()[0]
    private val movieId = data.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(dataRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = data

        `when`(dataRepository.getMovieById(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovie().value as MovieEntity
        verify(dataRepository).getMovieById(movieId)
        assertNotNull(movieEntity)
        assertEquals(data.id, movieEntity.id)
        assertEquals(data.title, movieEntity.title)
        assertEquals(data.poster_path, movieEntity.poster_path)
        assertEquals(data.vote_average, movieEntity.vote_average)
        assertEquals(data.overview, movieEntity.overview)
        assertEquals(data.release_date, movieEntity.release_date)
        assertEquals(data.baseURL, movieEntity.baseURL)

        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(data)
    }
}