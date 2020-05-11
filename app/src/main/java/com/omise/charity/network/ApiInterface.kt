package com.omise.charity.network

import com.omise.charity.network.dto.CharityListDto
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {

    @GET("charities")
    fun getCharityList(): Single<CharityListDto>
}