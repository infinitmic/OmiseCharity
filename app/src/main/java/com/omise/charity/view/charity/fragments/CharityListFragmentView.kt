package com.omise.charity.view.charity.fragments

import com.omise.charity.network.dto.CharityList

interface CharityListFragmentView {
    var refresh: Boolean
    fun showMessage(data: String)
    fun show(item: CharityList)
    fun showError(error: Throwable)
}