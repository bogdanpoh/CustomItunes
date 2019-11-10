package com.bogdanpoh.customitunes.presents

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bogdanpoh.customitunes.R
import com.bogdanpoh.customitunes.models.MainModel
import com.bogdanpoh.customitunes.providers.MainProvider
import com.bogdanpoh.customitunes.views.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    //type for a function loadData
    companion object Types{
        const val audiobook = "audiobook"
        const val book = "book"
        const val movie = "movie"
        const val podcast = "podcast"
    }

    fun loadData(type: String) {
        viewState.startLoading()
        MainProvider(presenter = this).loadData(type)
    }

    fun loadedAnswers(mainModel: MainModel) {
        viewState.endLoading()

        if (mainModel.feed.results.size == 0) {
            viewState.setupEmptyList()
            viewState.showError(message = R.string.empty_list)
        } else {
            viewState.setupAnswersList(mainModel = mainModel)
        }
    }
}