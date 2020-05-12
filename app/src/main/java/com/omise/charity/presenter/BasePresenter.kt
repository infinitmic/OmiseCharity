package com.omise.charity.presenter

import com.omise.charity.util.rx.plusAssign
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter : Presenter {

    protected var subscriptions = CompositeDisposable()

    fun bindToLifecycle(disposable: Disposable) {
        subscriptions += disposable
    }

    override fun onViewDestroyed() {
        subscriptions.dispose()
    }
}