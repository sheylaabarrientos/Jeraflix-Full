package com.sheyla.mymovies.data.remotesource

import com.sheyla.mymovies.data.model.movies.ResponseMovies
import io.reactivex.Single
import retrofit2.http.GET

interface MoviesRemoteSource {
    @GET("movie/popular")
    fun getPopularMovies(): Single<ResponseMovies>
}