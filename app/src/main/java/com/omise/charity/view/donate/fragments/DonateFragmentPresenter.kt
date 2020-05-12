package com.omise.charity.view.donate.fragments


import com.omise.charity.model.Donate
import com.omise.charity.model.DonateForm
import com.omise.charity.model.DonateResult
import com.omise.charity.network.HttpCallFailureException
import com.omise.charity.network.NoNetworkException
import com.omise.charity.network.OmiseRepository
import com.omise.charity.network.ServerUnreachableException
import com.omise.charity.presenter.BasePresenter
import com.omise.charity.util.rx.SchedulersCoupler
import com.omise.charity.util.rx.applySchedulers
import com.omise.charity.util.rx.plusAssign
import com.omise.charity.util.rx.subscribeBy
import com.omise.charity.view.donate.fragments.helper.DonateErrorMessage
import com.omise.charity.view.donate.fragments.helper.DonateUIValidator
import javax.inject.Inject

class DonateFragmentPresenter @Inject constructor(
    val view: DonateFragmentView,
    private val uiValidator: DonateUIValidator,
    private val errorMessageHandler: DonateErrorMessage,
    private val schedulers: SchedulersCoupler,
    private val omiseRepository: OmiseRepository
) :
    BasePresenter() {

    fun donate(donateForm: DonateForm) {
        view.clearErrors()
        view.disableAllInput()
        view.showProgress()

        if (!uiValidator.isNameValid(donateForm.name)) {
            view.hideProgress()
            view.enableAllInput()
            view.invalidName(errorMessageHandler.invalidName())
            return
        }

        if (!uiValidator.isTokenValid(donateForm.token)) {
            view.hideProgress()
            view.enableAllInput()
            view.invalidToken(errorMessageHandler.invalidToken())
            return
        }

        if (!uiValidator.isAmountValid(donateForm.amount)) {
            view.hideProgress()
            view.enableAllInput()
            view.invalidAmount(errorMessageHandler.invalidAmount())
            return
        }

        subscriptions += omiseRepository
            .donate(donateForm)
            .applySchedulers()
            .doOnSubscribe { view.showProgress() }
            .doFinally { view.hideProgress() }
            .subscribeBy(
                onSuccess = ::onApiSuccess,
                onError = ::displayError
            )

    }

    private fun onApiSuccess(donateResult: DonateResult) {
        view.hideProgress()
        view.enableAllInput()
        when ((donateResult as Donate).statusCode) {
            200 -> {
                view.onDonateSuccess()
            }
            201 -> {
                view.onDonateFailure((donateResult).errorMessage)
            }
            else -> {
                view.onDonateFailure((donateResult).errorMessage)
            }
        }
    }

    private fun displayError(error: Throwable) = when (error) {
        is NoNetworkException -> {
            view.hideProgress()
            view.enableAllInput()
            view.displayNoNetworkError()
        }
        is ServerUnreachableException -> {
            view.hideProgress()
            view.enableAllInput()
            view.displayServerUnreachableError()
        }
        is HttpCallFailureException -> {
            view.hideProgress()
            view.enableAllInput()
            view.displayCallFailedError()
        }
        else -> {
            view.hideProgress()
            view.enableAllInput()
            view.showError(error)
        }
    }
}