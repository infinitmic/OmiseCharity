package com.omise.charity.network

import com.google.gson.Gson
import io.reactivex.Single
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


val gson: Gson = Gson()

inline fun <reified T : R, R> Single<out R>.mapError(): Single<R> =
    this.map { it }
        .onErrorResumeNext { error ->
            if (error is HttpException && error.code() >= 400) {
                mapErrorBody(error, T::class.java)?.let {
                    Single.just(it)
                } ?: Single.error(IllegalStateException("Mapping http body failed!"))
            } else {
                Single.error(error)
            }
        }

fun <T> mapErrorBody(error: HttpException, type: Class<T>) =
    error.response().errorBody()?.let {
        gson
            .getAdapter(type)
            .fromJson(
                it.string()
            )
    }

fun <T : DomainMappable<R>, R> Single<T>.mapToDomain(): Single<R> = this.map { it.asDomain() }

fun <T> Single<T>.mapNetworkErrors(): Single<T> =
    this.onErrorResumeNext { error ->
        when (error) {
            is SocketTimeoutException -> Single.error(NoNetworkException(error))
            is UnknownHostException -> Single.error(ServerUnreachableException(error))
            is HttpException -> Single.error(HttpCallFailureException(error))
            else -> Single.error(error)
        }
    }