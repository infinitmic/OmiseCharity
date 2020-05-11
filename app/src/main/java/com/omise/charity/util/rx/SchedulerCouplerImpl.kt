package com.omise.charity.util.rx

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulersCouplerImpl : SchedulersCoupler {
    override fun <T> convertToAsyncFlowable(): FlowableTransformer<T, T> =
        FlowableTransformer {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }

    override fun <T> convertToAsyncObserver(): ObservableTransformer<T, T> =
        ObservableTransformer {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }

    override fun <T> convertToAsyncSingle(): SingleTransformer<T, T> =
        SingleTransformer {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }

    override fun convertToAsyncCompletable(): CompletableTransformer =
        CompletableTransformer {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }

    override fun <T> convertToAsyncMaybe(): MaybeTransformer<T, T> =
        MaybeTransformer {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
}