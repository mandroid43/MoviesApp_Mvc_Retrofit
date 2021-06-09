package com.manapps.mandroid.moviesapp_mvc_ret.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.manapps.mandroid.moviesapp_mvc_ret.R
import com.manapps.mandroid.moviesapp_mvc_ret.databinding.ActivityMainBinding
import com.manapps.mandroid.moviesapp_mvc_ret.models.Movies
import com.manapps.mandroid.moviesapp_mvc_ret.models.MoviesResponse
import com.manapps.mandroid.moviesapp_mvc_ret.networking.ApiService
import com.manapps.mandroid.moviesapp_mvc_ret.utils.NetworkHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var retrofitService: ApiService
    private var moviesList: List<Movies> = arrayListOf()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindings()
        setUpRecyclerView()


        retrofitService = RetrofitBuilder.getApiService()
        if (NetworkHelper.isNetworkConnected(this)) {
            setProgressBarVisible()
            fetchMovies()
        } else {
            setProgressBarGone()
            binding.moviesRv.isVisible = false
            binding.errorView.isVisible = true
            binding.errorView.text = resources.getString(R.string.no_network_connected)
        }
    }

    private fun initBindings() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setUpRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.moviesRv.layoutManager=linearLayoutManager
    }

    private fun fetchMovies() {
        val apiKey: String = resources.getString(R.string.API_KEY)
        val getMoviesCall = retrofitService.getMovies(apiKey)
        getMoviesCall.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                setProgressBarGone()
                if (response.isSuccessful && response.body() != null) {
                    moviesList = response.body()!!.results
                    setUpMoviesRvView(moviesList)
                } else {
                    setUpMoviesRvView(moviesList)
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                setProgressBarGone()
                setUpMoviesRvView(moviesList)
            }
        })
    }

    private fun setUpMoviesRvView(moviesList: List<Movies>) {
        if (moviesList.isNotEmpty()) {
            binding.errorView.isVisible = false
            binding.moviesRv.isVisible = true
            linearLayoutManager = LinearLayoutManager(this)
            moviesAdapter = MoviesAdapter(this, moviesList)
            binding.moviesRv.adapter = moviesAdapter
            moviesAdapter.notifyDataSetChanged()
        } else {
            binding.moviesRv.isVisible = false
            binding.errorView.isVisible = true
            binding.errorView.text = resources.getString(R.string.error_msg)

        }
    }

    private fun setProgressBarVisible() {
        binding.progressBar.isVisible = true
    }

    private   fun setProgressBarGone() {
        binding.progressBar.isVisible = false
    }
}