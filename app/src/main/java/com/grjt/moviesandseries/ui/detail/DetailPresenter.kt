package com.grjt.moviesandseries.ui.detail

import com.grjt.moviesandseries.model.database.Movie


class DetailPresenter {

    private var view: View? = null

    interface View {
        fun updateUI(movie: Movie)
    }

    fun onCreate(view: View, movie: Movie) {
        this.view = view
        view.updateUI(movie)
    }

    fun onDestroy() {
        view = null
    }

}