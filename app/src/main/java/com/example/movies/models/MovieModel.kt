package com.example.movies.models

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("adult")
    var adult: Boolean?,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("genre_ids")
    var genreIds: ArrayList<Int>?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("origin_country")
    var originCountry: ArrayList<String>?,
    @SerializedName("original_language")
    var originalLanguage: String?,
    @SerializedName("original_name")
    var originalName: String?,
    @SerializedName("overview")
    var overview: String?,
    @SerializedName("popularity")
    var popularity: Float?,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("first_air_date")
    var firstAirDate: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("vote_average")
    var voteAverage: Float?,
    @SerializedName("vote_count")
    var voteCount: Int?,
)