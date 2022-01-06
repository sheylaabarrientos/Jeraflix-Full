package com.sheyla.mymovies.data.model.movies

import com.google.gson.annotations.SerializedName

class MovieInfoResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    var isFavorite: Boolean = false,
)