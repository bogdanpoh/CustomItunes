package com.bogdanpoh.customitunes.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleTagStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.bogdanpoh.customitunes.models.MainModel

@StateStrategyType(value = AddToEndSingleTagStrategy::class)
interface MainView : MvpView {
    fun startLoading()
    fun endLoading()
    fun showError(message: Int)

    fun setupEmptyList()
    fun setupAnswersList(mainModel: MainModel)
}