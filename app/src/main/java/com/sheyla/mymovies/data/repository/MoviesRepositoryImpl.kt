package com.sheyla.mymovies.data.repository

import com.sheyla.mymovies.data.localsource.MovieLocalDataSourceImpl
import com.sheyla.mymovies.data.mappers.MovieMapper
import com.sheyla.mymovies.data.remotesource.MoviesRemoteSource
import com.sheyla.mymovies.domain.Movie
import io.reactivex.Single

class MoviesRepositoryImpl : MoviesRepository {
    private val moviesRemoteSource: MoviesRemoteSource =
        com.sheyla.mymovies.data.base.Network.getMoviesRemoteSource()
    private val movieLocalDataSource = MovieLocalDataSourceImpl
    private val movieMapper = MovieMapper()

    override fun getPopularMovies(): Single<List<Movie>> {
        return moviesRemoteSource
            .getPopularMovies()
            .flatMap { movieResponseList ->
                movieLocalDataSource
                    .getFavoriteMovies()
                    .map { favoriteMovieList ->
                        movieResponseList.movieResults.forEach { movieResponse ->
                            val result = favoriteMovieList.any { favoriteMovie ->
                                favoriteMovie.id == movieResponse.id
                            }
                            movieResponse.isFavorite = result
                        }
                        movieResponseList.movieResults
                    }
            }
            .map {
                movieMapper.map(it)
            }
    }

}