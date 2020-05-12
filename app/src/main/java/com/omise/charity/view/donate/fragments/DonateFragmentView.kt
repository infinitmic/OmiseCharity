package com.omise.charity.view.donate.fragments

interface DonateFragmentView {
    fun showError(error: Throwable)
    fun clearErrors()
    fun clearAmountError()
    fun invalidAmount(errorMsg: String)
    fun onDonateSuccess()
    fun onDonateFailure(errorMsg: String?)
    fun showProgress()
    fun hideProgress()
    fun enableAllInput()
    fun disableAllInput()
    fun removeCallbacks()
    fun displayNoNetworkError()
    fun displayServerUnreachableError()
    fun displayCallFailedError()
    fun displayGenericErrorMessage(errorMsg: String?)
}