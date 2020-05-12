package com.omise.charity.network

import com.omise.charity.model.CharitiesResult
import com.omise.charity.model.DonateForm
import com.omise.charity.model.DonateResult
import com.omise.charity.network.dto.*
import io.reactivex.Single

class OmiseRepositoryImpl(private val apiInterface: ApiInterface) : OmiseRepository {

    override fun getCharities(): Single<CharitiesResult> {
        return apiInterface.getCharityList()
            .map { CharitiesSuccessResponse(it) }
            .mapError<CharitiesFailureResponse, CharitiesApiResponse>()
            .mapNetworkErrors()
            .mapToDomain()
    }

    override fun donate(donateForm: DonateForm): Single<DonateResult> {
        return apiInterface.donate(donateForm)
            .map { DonateSuccessResponse(it) }
            .mapError<DonateFailureResponse, DonateApiResponse>()
            .mapNetworkErrors()
            .mapToDomain()
    }
}