package com.omise.charity.network.dto

import com.google.gson.annotations.SerializedName

data class CharityList(
    @SerializedName("total") val totalResults: Int?,
    @SerializedName("data") val charities: List<Charity>?
)