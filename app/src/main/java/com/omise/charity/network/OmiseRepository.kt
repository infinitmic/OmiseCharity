package com.omise.charity.network

import com.omise.charity.model.CharitiesResult
import com.omise.charity.model.DonateForm
import com.omise.charity.model.DonateResult
import io.reactivex.Single

interface OmiseRepository {
    fun getCharities(): Single<CharitiesResult>
    fun donate(donateForm: DonateForm): Single<DonateResult>
}