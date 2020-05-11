package com.omise.charity.network.dto

import com.omise.charity.model.Donate
import com.omise.charity.model.DonateResult
import com.omise.charity.network.DomainMappable


interface DonateApiResponse : DomainMappable<DonateResult> {
    override fun asDomain(): DonateResult
}

data class DonateSuccessResponse(
    private val donateDto: DonateDto
) : DonateApiResponse {
    override fun asDomain() =
        Donate(donateDto.success, donateDto.errorCode, donateDto.errorMessage)
}

data class DonateFailureResponse(
    private val donateDto: DonateDto
) : DonateApiResponse {
    override fun asDomain() =
        Donate(donateDto.success, donateDto.errorCode, donateDto.errorMessage)
}


