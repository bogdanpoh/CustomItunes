package com.bogdanpoh.customitunes.providers

import com.bogdanpoh.customitunes.R
import com.bogdanpoh.customitunes.apiServices.ItunesApiService
import com.bogdanpoh.customitunes.models.MainModel
import com.bogdanpoh.customitunes.presents.MainPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainProvider(var presenter: MainPresenter) {

    private val TAG = "MainProvider"
    private val baseUrl = "https://rss.itunes.apple.com/"

    private val retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val api = retrofit.create(ItunesApiService::class.java)

    var model = null

    fun loadData(type: String) {

        val query = when (type) {
            MainPresenter.audiobook ->{
                api.getAudiobooks()

            }
            MainPresenter.movie -> api.getMovies()
            MainPresenter.podcast -> api.getPodcasts()
            else -> api.getAudiobooks()
        }

        query.enqueue(object : Callback<MainModel> {

            override fun onFailure(call: Call<MainModel>, t: Throwable) {
                presenter.viewState.setupEmptyList()
                presenter.viewState.showError(R.string.empty_list)
            }

            override fun onResponse(
                call: Call<MainModel>,
                response: Response<MainModel>
            ) {
                val result = response.body() as MainModel

                presenter.loadedAnswers(mainModel = result)
            }

        })
    }

}
