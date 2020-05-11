package com.omise.charity.network.dto

import com.omise.charity.model.*
import com.omise.charity.network.DomainMappable


interface CharitiesApiResponse : DomainMappable<CharitiesResult> {
    override fun asDomain(): CharitiesResult
}

data class CharitiesSuccessResponse(
    private val charitiesApiResult: CharityListDto
) : CharitiesApiResponse {
    override fun asDomain() = CharitiesSuccess(
        CharityList(
            charitiesApiResult.totalResults,
            charitiesApiResult.charityDtos?.flatMap { it ->
                listOf(
                    Charity(
                        it.id,
                        it.name,
                        it.urlToImage
                    )
                )
            }
        )
    )
}

class CharitiesFailureResponse : CharitiesApiResponse {
    override fun asDomain() = CharitiesFailure()
}
