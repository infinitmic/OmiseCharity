package com.omise.charity.util.rx

import io.reactivex.*

interface SchedulersCoupler {

    fun <T> convertToAsyncFlowable(): FlowableTransformer<T, T>

    fun <T> convertToAsyncObserver(): ObservableTransformer<T, T>

    fun <T> convertToAsyncSingle(): SingleTransformer<T, T>

    fun convertToAsyncCompletable(): CompletableTransformer

    fun <T> convertToAsyncMaybe(): MaybeTransformer<T, T>

}