package com.omise.charity.application.di

import android.app.Application
import com.omise.charity.application.OmiseCharityApplication
import com.omise.charity.network.RetrofitModule
import dagger.BindsInstance
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
        NetworkModule::class,
        ResourcesProvider::class
    )
)
interface AppComponent : AndroidInjector<OmiseCharityApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<OmiseCharityApplication>(){
        @BindsInstance
        abstract fun application(application: Application): Builder
    }
}