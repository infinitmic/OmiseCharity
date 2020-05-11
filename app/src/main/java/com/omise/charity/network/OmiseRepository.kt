package com.omise.charity.network

import com.omise.charity.network.dto.CharityList
import io.reactivex.Single

class OmiseRepository(private val apiInterface: ApiInterface) {

    fun getTopHeadlines(): Single<CharityList> {
        return apiInterface.getCharityList()
    }
}