package com.bogdanpoh.customitunes.providers

import com.bogdanpoh.customitunes.apiServices.ItunesApiService
import com.bogdanpoh.customitunes.models.AudiobookModel
import com.bogdanpoh.customitunes.models.MovieModel
import com.bogdanpoh.customitunes.models.PodcastModel
import com.bogdanpoh.customitunes.presents.MainPresenter
import com.bogdanpoh.customitunes.presents.ShowPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ShowProvider(var presenter: ShowPresenter) {

    private val TAG = "ShowProvider"

    fun loadShow(id: Int, type: String) {

        val baseUrl = "https://itunes.apple.com/"

        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api = retrofit.create(ItunesApiService::class.java)

        val getBookOrAudiobook = {
            api.getAudiobook(id).enqueue(object : Callback<AudiobookModel> {

                override fun onFailure(call: Call<AudiobookModel>, t: Throwable) {
                    presenter.showError(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<AudiobookModel>,
                    response: Response<AudiobookModel>
                ) {
                    val result = response.body() as AudiobookModel

                    presenter.loadedShow(audiobookModel = result)
                }

            })
        }

        when (type) {
            MainPresenter.audiobook -> getBookOrAudiobook()
            MainPresenter.book -> getBookOrAudiobook()
            MainPresenter.movie -> {
                api.getMovie(id).enqueue(object : Callback<MovieModel> {
                    override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                        presenter.showError(t.localizedMessage)
                    }

                    override fun onResponse(
                        call: Call<MovieModel>,
                        response: Response<MovieModel>
                    ) {
                        val result = response.body() as MovieModel

                        presenter.loadedShow(movieModel = result)
                    }

                })
            }

            MainPresenter.podcast -> {
                api.getPodcast(id).enqueue(object : Callback<PodcastModel> {
                    override fun onFailure(call: Call<PodcastModel>, t: Throwable) {
                        presenter.showError(t.localizedMessage)
                    }

                    override fun onResponse(
                        call: Call<PodcastModel>,
                        response: Response<PodcastModel>
                    ) {
                        val result = response.body() as PodcastModel

                        presenter.loadedShow(podcastModel = result)
                    }

                })
            }
        }
    }


}