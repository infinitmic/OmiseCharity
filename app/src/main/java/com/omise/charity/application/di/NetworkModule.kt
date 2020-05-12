package com.omise.charity.application.di

import com.omise.charity.network.ApiInterface
import com.omise.charity.network.OmiseRepository
import com.omise.charity.network.OmiseRepositoryImpl
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
    fun providesOmiseRepository(apiInterface: ApiInterface): OmiseRepository =
        OmiseRepositoryImpl(apiInterface)
}