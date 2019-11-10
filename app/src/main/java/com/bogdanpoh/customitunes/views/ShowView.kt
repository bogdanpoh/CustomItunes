package com.bogdanpoh.customitunes.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleTagStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.bogdanpoh.customitunes.models.AudiobookModel
import com.bogdanpoh.customitunes.models.MovieModel
import com.bogdanpoh.customitunes.models.PodcastModel

@StateStrategyType(value = AddToEndSingleTagStrategy::class)
interface ShowView : MvpView {
    fun errorLoad(message: String)

    fun viewControls()

    fun bindAudiobook(audiobookModel: AudiobookModel)
    fun bindMovie(movieModel: MovieModel)
    fun bindPodcast(podcastModel: PodcastModel)
}