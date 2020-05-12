package com.omise.charity.view.donate.fragments.helper

import com.omise.charity.R
import com.omise.charity.util.common.ResourcesValueProvider
import javax.inject.Inject

class DonateErrorMessageImpl @Inject constructor(val resourcesValueProvider: ResourcesValueProvider) :
    DonateErrorMessage {
    override fun invalidName(): String {
        return resourcesValueProvider.provideStringValue(R.string.label_invalid_name)
    }

    override fun invalidAmount(): String {
        return resourcesValueProvider.provideStringValue(R.string.label_invalid_amount)
    }
}