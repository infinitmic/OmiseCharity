package com.omise.charity.network.dto

import com.omise.charity.model.Donate
import com.omise.charity.model.DonateResult
import com.omise.charity.network.DomainMappable


interface DonateApiResponse : DomainMappable<DonateResult> {
    override fun asDomain(): DonateResult
}

data class DonateSuccessResponse(
    private val doneDtoWrapper: DonateDtoWrapper
) : DonateApiResponse {
    override fun asDomain() =
        Donate(
            doneDtoWrapper.statusCode,
            doneDtoWrapper.responseData?.success,
            doneDtoWrapper.responseData?.errorCode,
            doneDtoWrapper.responseData?.errorMessage
        )
}

data class DonateFailureResponse(
    private val doneDtoWrapper: DonateDtoWrapper
) : DonateApiResponse {
    override fun asDomain() =
        Donate(
            doneDtoWrapper.statusCode,
            doneDtoWrapper.responseData?.success,
            doneDtoWrapper.responseData?.errorCode,
            doneDtoWrapper.responseData?.errorMessage
        )
}


