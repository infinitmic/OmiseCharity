package com.omise.charity.util.common

import androidx.annotation.StringRes

interface ResourcesValueProvider{
    fun provideStringValue(@StringRes stringRes: Int) : String
}