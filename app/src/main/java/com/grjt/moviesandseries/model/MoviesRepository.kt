package com.grjt.moviesandseries.model

import android.app.Activity
import com.grjt.moviesandseries.R

class MoviesRepository(activity: Activity) {

    private val apiKey = activity.getString(R.string.api_key)

    suspend fun findPopularMovies() =
        MovieDb.service
            .listPopularMoviesAsync(apiKey)

    suspend fun findNowMovies() =
        MovieDb.service
            .listNowPayingMoviesAsync(apiKey)

}