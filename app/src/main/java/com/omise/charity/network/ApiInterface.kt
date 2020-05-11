package com.omise.charity.network

import com.omise.charity.network.dto.CharityList
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {

    @GET("charities")
    fun getCharityList(): Single<CharityList>
}