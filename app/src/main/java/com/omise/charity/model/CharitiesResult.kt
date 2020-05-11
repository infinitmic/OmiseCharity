package com.omise.charity.model

interface CharitiesResult

data class CharitiesSuccess(
    val charityList: CharityList
) : CharitiesResult

class CharitiesFailure : CharitiesResult

data class Charity (
    val id: Int?,
    val name: String?,
    val urlToImage: String?
)

data class CharityList(
    val totalResults: Int?,
    val charityList: List<Charity>?
)