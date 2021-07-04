package com.dicoding.moviecatalogue.data.source.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
        val id: String,
        val title: String,
        val overview: String?,
        val poster_path: String,
        val release_date: String,
        val vote_average: String
) : Parcelable {
    val baseURL: String get() = "https://image.tmdb.org/t/p/w500"
}