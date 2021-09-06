package com.grjt.moviesandseries.model.server

import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {

    @GET("movie/now_playing?api_key=1bb49643f80bd193b593fb82b5f88a2a&language=es-MX&page=1")
    suspend fun listNowPayingMoviesAsync(
        @Query("api_key") apiKey: String
    ): MovieDbResult

    @GET("movie/popular?api_key=1bb49643f80bd193b593fb82b5f88a2a&language=es-MX&page=2")
    suspend fun listPopularMoviesAsync(
        @Query("api_key") apiKey: String
    ): MovieDbResult

}