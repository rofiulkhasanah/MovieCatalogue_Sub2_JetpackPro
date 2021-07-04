package com.dicoding.moviecatalogue.di

import android.content.Context
import com.dicoding.moviecatalogue.data.DataRepository
import com.dicoding.moviecatalogue.data.source.remote.RemoteDataSource
import com.dicoding.moviecatalogue.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): DataRepository {
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return DataRepository.getInstance(remoteDataSource)

    }
}