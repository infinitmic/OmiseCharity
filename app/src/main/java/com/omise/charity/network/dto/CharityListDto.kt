package com.omise.charity.network.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharityListDto(
    @SerializedName("total") val totalResults: Int?,
    @SerializedName("data") val charityDtos: List<CharityDto>?
)

data class CharityListDtoWrapper(
    @SerializedName("status_code") val statusCode: Int,
    @SerializedName("response_data") val responseData: CharityListDto?
) : Serializable