package com.omise.charity.network.dto

import com.google.gson.annotations.SerializedName

data class CharityListDto(
    @SerializedName("total") val totalResults: Int?,
    @SerializedName("data") val charityDtos: List<CharityDto>?
)