package com.omise.charity.network.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Charity(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("logo_url") val urlToImage: String?
) : Serializable