package com.omise.charity.application.di

import com.omise.charity.application.OmiseCharityApplication
import com.omise.charity.network.RetrofitModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AppModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        RetrofitModule::class,
        NetworkModule::class
    )
)
interface AppComponent : AndroidInjector<OmiseCharityApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<OmiseCharityApplication>()
}