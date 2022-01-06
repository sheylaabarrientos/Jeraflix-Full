package com.sheyla.mymovies.data.localsource

import com.sheyla.mymoviesdata.model.movies.MovieResponse
import io.reactivex.Single

object MovieLocalDataSourceImpl : MovieLocalDataSource {

    private val favoriteMoviesList = mutableListOf<MovieResponse>()

    override fun getFavoriteMovies(): Single<List<MovieResponse>> {
        return Single.create { emitter ->
            emitter.onSuccess(favoriteMoviesList)
        }
    }
}