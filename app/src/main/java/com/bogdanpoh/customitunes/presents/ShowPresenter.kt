package com.bogdanpoh.customitunes.presents

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bogdanpoh.customitunes.models.AudiobookModel
import com.bogdanpoh.customitunes.models.MovieModel
import com.bogdanpoh.customitunes.models.PodcastModel
import com.bogdanpoh.customitunes.providers.ShowProvider
import com.bogdanpoh.customitunes.views.ShowView
import org.jsoup.Jsoup
import java.text.SimpleDateFormat

@InjectViewState
class ShowPresenter : MvpPresenter<ShowView>() {

    private val TAG = "ShowPresenter"

    fun loadShow(id: Int, type: String) {
        ShowProvider(presenter = this).loadShow(id = id, type = type)
    }

    fun loadedShow(audiobookModel: AudiobookModel) {
        viewState.bindAudiobook(audiobookModel = audiobookModel)
    }

    fun loadedShow(movieModel: MovieModel) {
        viewState.bindMovie(movieModel = movieModel)
    }

    fun loadedShow(podcastModel: PodcastModel) {
        viewState.bindPodcast(podcastModel = podcastModel)
    }

    fun showError(message: String) {
        viewState.errorLoad(message)
    }

    fun convertDate(date: String): String {

        val format = SimpleDateFormat("yyyy-MM-dd")

        val result = format.parse(date.split('T')[0]).toString().split(' ')

        return result[2] + ' ' + result[1] + ", " + result[5]
    }

    fun convertHtmlToString(url: String): String {
        val document = Jsoup.parse(url)
        return document.body().text()
    }

}