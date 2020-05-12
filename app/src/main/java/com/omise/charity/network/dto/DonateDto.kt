package com.omise.charity.network.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DonateDto(
    @SerializedName("success") val success: Boolean?,
    @SerializedName("error_code") val errorCode: String?,
    @SerializedName("error_message") val errorMessage: String?
) : Serializable

data class DonateDtoWrapper(
    @SerializedName("status_code") val statusCode: Int,
    @SerializedName("response_data") val responseData: DonateDto?
) : Serializable