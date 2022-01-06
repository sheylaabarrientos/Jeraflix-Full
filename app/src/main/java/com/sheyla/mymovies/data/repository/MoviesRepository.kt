package com.sheyla.mymovies.data.repository

import com.sheyla.mymovies.domain.Movie
import io.reactivex.Single

interface MoviesRepository {
    fun getPopularMovies(): Single<List<Movie>>
}