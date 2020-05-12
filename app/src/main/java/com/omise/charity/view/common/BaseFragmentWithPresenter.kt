package com.omise.charity.view.common

import android.os.Bundle
import com.omise.charity.presenter.Presenter

abstract class BaseFragmentWithPresenter : BaseFragment() {

    abstract val presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}