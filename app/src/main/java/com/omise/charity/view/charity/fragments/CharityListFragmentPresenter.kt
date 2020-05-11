package com.omise.charity.view.charity.fragments

import com.omise.charity.model.CharitiesFailure
import com.omise.charity.model.CharitiesResult
import com.omise.charity.model.CharitiesSuccess
import com.omise.charity.network.HttpCallFailureException
import com.omise.charity.network.NoNetworkException
import com.omise.charity.network.OmiseRepository
import com.omise.charity.network.ServerUnreachableException
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
            .getCharities()
            .applySchedulers()
            .doOnSubscribe { view.refresh = true }
            .doFinally { view.refresh = false }
            .subscribeBy(
                onSuccess = ::onApiSuccess,
                onError = ::displayError
            )
    }

    private fun onApiSuccess(charitiesResult: CharitiesResult) = when (charitiesResult) {
        is CharitiesSuccess -> {
            view.show(charitiesResult.charityList)
        }
        is CharitiesFailure -> view.displayGenericErrorMessage("Loading Failed!")
        else -> view.displayGenericErrorMessage("Loading Failed!")
    }

    private fun displayError(error: Throwable) = when (error) {
        is NoNetworkException -> {
            view.displayNoNetworkError()
        }
        is ServerUnreachableException -> {
            view.displayServerUnreachableError()
        }
        is HttpCallFailureException -> {
            view.displayCallFailedError()
        }
        else -> view.showError(error)
    }
}