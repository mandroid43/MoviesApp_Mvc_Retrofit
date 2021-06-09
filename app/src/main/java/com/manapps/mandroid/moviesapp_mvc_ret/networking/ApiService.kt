package com.manapps.mandroid.moviesapp_mvc_ret.networking

import com.manapps.mandroid.moviesapp_mvc_ret.models.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    fun getMovies(@Query("api_key") key: String): Call<MoviesResponse>
}