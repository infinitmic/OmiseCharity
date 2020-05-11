package com.omise.charity.view.charity.fragments

import com.omise.charity.model.CharityList

interface CharityListFragmentView {
    var refresh: Boolean
    fun showMessage(data: String)
    fun show(item: CharityList)
    fun showError(error: Throwable)
    fun displayNoNetworkError()
    fun displayServerUnreachableError()
    fun displayCallFailedError()
    fun displayGenericErrorMessage(errorMsg: String?)
}