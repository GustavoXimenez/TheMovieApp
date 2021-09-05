package com.grjt.moviesandseries.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.grjt.moviesandseries.*
import com.grjt.moviesandseries.databinding.ActivityMovieBinding
import com.grjt.moviesandseries.model.Movie
import com.grjt.moviesandseries.model.MoviesRepository
import com.grjt.moviesandseries.ui.detail.DetailActivity
import com.grjt.moviesandseries.ui.startActivity

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private val presenter by lazy { MainPresenter(MoviesRepository(this)) }
    private val adapter = MovieAdapter { presenter.onMovieClicked(it) }
    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater).apply {
            setContentView(root)
            presenter.onCreate(this@MainActivity)
            recycler.adapter = adapter
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progress.visibility = View.GONE
    }

    override fun updateData(movies: List<Movie>) {
        adapter.movies = movies
    }

    override fun navigateTo(movie: Movie) {
        startActivity<DetailActivity> {
            putExtra(DetailActivity.MOVIE, movie)
        }
    }

}