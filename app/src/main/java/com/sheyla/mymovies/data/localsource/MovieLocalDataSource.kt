package com.sheyla.mymovies.data.localsource

import com.sheyla.mymoviesdata.model.movies.MovieResponse
import io.reactivex.Single

interface MovieLocalDataSource {
    fun getFavoriteMovies(): Single<List<MovieResponse>>
}