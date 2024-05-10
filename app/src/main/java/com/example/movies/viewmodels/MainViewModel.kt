package com.example.movies.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.movies.BuildConfig
import com.example.movies.models.MovieModel
import com.example.movies.retrofit.ApiRepository
import com.example.movies.retrofit.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "m-vm"

class MainViewModel : ViewModel() {

    val moviesList = mutableStateListOf<MovieModel>()

    private var pageNumber = 0

    private val repository: ApiRepository = ApiRepository(RetrofitService.getInstance() )

    fun getMovies() = CoroutineScope(Dispatchers.IO).launch {
        getMovies(com.example.movies.BuildConfig.API_KEY, ++pageNumber)
    }

    suspend fun getMovies(apiKey: String, page: Int) = repository.getMovies(
        params = mapOf(
            Pair("api_key", apiKey),
            Pair("page", page.toString()),
        )
    ) response@ { response->
        if (response == null) return@response
        response.results?.let { moviesList.addAll(it) }
        Log.i(TAG, "movies size: ${moviesList.size}")
    }

}