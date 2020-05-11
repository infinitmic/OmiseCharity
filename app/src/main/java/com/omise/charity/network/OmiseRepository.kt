package com.omise.charity.network

import com.omise.charity.model.CharitiesResult
import com.omise.charity.network.dto.CharitiesApiResponse
import com.omise.charity.network.dto.CharitiesFailureResponse
import com.omise.charity.network.dto.CharitiesSuccessResponse
import io.reactivex.Single

class OmiseRepository(private val apiInterface: ApiInterface) {

    fun getCharities(): Single<CharitiesResult> {
        return apiInterface.getCharityList()
            .map { CharitiesSuccessResponse(it) }
            .mapError<CharitiesFailureResponse, CharitiesApiResponse>()
            .mapNetworkErrors()
            .mapToDomain()
    }
}