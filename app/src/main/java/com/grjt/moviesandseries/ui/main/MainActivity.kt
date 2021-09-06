package com.grjt.moviesandseries.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.grjt.moviesandseries.*
import com.grjt.moviesandseries.databinding.ActivityMovieBinding
import com.grjt.moviesandseries.model.database.Movie
import com.grjt.moviesandseries.model.server.MoviesRepository
import com.grjt.moviesandseries.ui.app
import com.grjt.moviesandseries.ui.detail.DetailActivity
import com.grjt.moviesandseries.ui.startActivity
import kotlinx.android.synthetic.main.activity_movie.*

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private val presenter by lazy { MainPresenter(MoviesRepository(app)) }
    private val adapterPopular = MovieAdapter { presenter.onMovieClicked(it) }
    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater).apply {
            setContentView(root)
            presenter.onCreate(this@MainActivity)
            recycler_popular.adapter = adapterPopular
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

    override fun updateData(moviesPopular: List<Movie>) {
        adapterPopular.movies = moviesPopular
    }

    override fun navigateTo(movie: Movie) {
        startActivity<DetailActivity> {
            putExtra(DetailActivity.MOVIE, movie)
        }
    }

}