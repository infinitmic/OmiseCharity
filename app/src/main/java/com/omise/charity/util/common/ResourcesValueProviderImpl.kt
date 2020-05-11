package com.omise.charity.util.common

import android.content.res.Resources
import javax.inject.Inject

class ResourcesValueProviderImpl @Inject constructor(val resources: Resources) :
    ResourcesValueProvider {

    override fun provideStringValue(stringRes: Int): String {
        return resources.getString(stringRes)
    }

}