package com.grjt.moviesandseries.model.server

import com.grjt.moviesandseries.MoviesApp
import com.grjt.moviesandseries.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.grjt.moviesandseries.model.database.Movie as DBMovie
import com.grjt.moviesandseries.model.server.Movie as ServerMovie

class MoviesRepository(application: MoviesApp) {

    private val apiKey = application.getString(R.string.api_key)
    private val db = application.db

    suspend fun findPopularMovies(): List<DBMovie> = withContext(Dispatchers.IO) {

        with(db.movieDao()) {
            if(movieCount() <= 0) {

                val movies = MovieDb.service
                    .listPopularMoviesAsync(apiKey)
                    .results

                insertMovies(movies.map(ServerMovie::convertToDbMovie))

            }

            getAll()
        }
    }

    suspend fun findById(id: Int): DBMovie = withContext(Dispatchers.IO) {
        db.movieDao().findById(id)
    }

    suspend fun update(movie: DBMovie) = withContext(Dispatchers.IO) {
        db.movieDao().updateMovie(movie)
    }

    suspend fun findNowMovies(): List<DBMovie> = withContext(Dispatchers.IO) {

        with(db.movieDao()) {
            if(movieCount() <= 0) {

                val movies = MovieDb.service
                    .listNowPayingMoviesAsync(apiKey)
                    .results

                insertMovies(movies.map(ServerMovie::convertToDbMovie))

            }
        getAll()
        }
    }

}

private fun ServerMovie.convertToDbMovie() = DBMovie(
    0,
    title,
    overview,
    releaseDate,
    posterPath,
    backdropPath ?: posterPath,
    originalLanguage,
    originalTitle,
    popularity,
    voteAverage,
    false
)