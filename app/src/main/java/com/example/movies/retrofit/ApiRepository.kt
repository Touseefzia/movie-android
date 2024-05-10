package com.example.movies.retrofit

import android.util.Log
import com.example.triviacompose.models.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "api-repo"

class ApiRepository(
    private val retrofitService: RetrofitService,
) {

    suspend fun getMovies(params: Map<String, String>, onResponse: (ApiResponse?)->Unit): Unit = withContext(Dispatchers.IO) {
        try {
            val response = retrofitService.getMovies(params = params)
            onResponse(response)
        } catch (e: Exception) {
            Log.w(TAG, "getMovies: ", e)
            onResponse(null)
        }

    }


}