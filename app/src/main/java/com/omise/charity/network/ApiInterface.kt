package com.omise.charity.network

import com.omise.charity.model.DonateForm
import com.omise.charity.network.dto.CharityListDto
import com.omise.charity.network.dto.CharityListDtoWrapper
import com.omise.charity.network.dto.DonateDto
import com.omise.charity.network.dto.DonateDtoWrapper
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiInterface {

    @GET("charities")
    fun getCharityList(): Single<CharityListDtoWrapper>

    @POST("donations")
    fun donate(@Body donateForm: DonateForm): Single<DonateDtoWrapper>
}