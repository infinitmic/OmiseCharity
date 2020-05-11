package com.omise.charity.view.charity.fragments

import com.omise.charity.network.OmiseRepository
import com.omise.charity.presenter.BasePresenter
import com.omise.charity.util.rx.applySchedulers
import com.omise.charity.util.rx.plusAssign
import com.omise.charity.util.rx.subscribeBy
import javax.inject.Inject

class CharityListFragmentPresenter @Inject constructor(
    val view: CharityListFragmentView,
    private val omiseRepository: OmiseRepository
) :
    BasePresenter() {

    fun onViewCreated() {
        loadData()
    }

    fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        subscriptions += omiseRepository
            .getTopHeadlines()
            .applySchedulers()
            .doOnSubscribe { view.refresh = true }
            .doFinally { view.refresh = false }
            .subscribeBy(
                onSuccess = view::show,
                onError = view::showError
            )
    }
}