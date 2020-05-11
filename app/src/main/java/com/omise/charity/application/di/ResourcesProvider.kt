package com.omise.charity.application.di

import android.content.Context
import android.content.res.Resources
import com.omise.charity.util.common.ResourcesValueProvider
import com.omise.charity.util.common.ResourcesValueProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ResourcesProvider {

    @Singleton
    @Provides
    fun providesResources(context: Context): Resources {
        return context.resources
    }

    @Provides
    @Singleton
    fun providesErrorMessage(resources: Resources): ResourcesValueProvider =
        ResourcesValueProviderImpl(resources)
}