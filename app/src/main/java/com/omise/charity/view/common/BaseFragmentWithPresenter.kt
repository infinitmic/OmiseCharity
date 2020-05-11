package com.omise.charity.view.common

import com.omise.charity.presenter.Presenter

abstract class BaseFragmentWithPresenter : BaseFragment() {

    abstract val presenter: Presenter

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}