package com.example.triviacompose.models

import com.example.movies.models.MovieModel
import com.google.gson.annotations.SerializedName

data class ApiResponse (
    @SerializedName("page" ) val page : Int?,
    @SerializedName("results") val results: ArrayList<MovieModel>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
)

