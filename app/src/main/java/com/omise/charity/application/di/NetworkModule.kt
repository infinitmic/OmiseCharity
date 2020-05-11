package com.omise.charity.application.di

import com.omise.charity.network.ApiInterface
import com.omise.charity.network.OmiseRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun providesNewsRepository(apiInterface: ApiInterface): OmiseRepository {
        return OmiseRepository(apiInterface)
    }
}