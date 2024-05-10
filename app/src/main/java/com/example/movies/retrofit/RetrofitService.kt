package com.example.movies.retrofit

import com.example.triviacompose.models.ApiResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RetrofitService {

    @GET(ApiConstants.GET_MOVIES)
    suspend fun getMovies(
    @QueryMap params: Map<String, String>): ApiResponse

    companion object {

        var retrofitService: RetrofitService? = null

        //Create the Retrofit service instance using the retrofit.
        fun getInstance(): RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}