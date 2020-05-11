package com.omise.charity.application.di

import android.app.Application
import android.content.Context
import com.omise.charity.util.rx.SchedulersCoupler
import com.omise.charity.util.rx.SchedulersCouplerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(app: Application): Context = app

    @Provides
    @Singleton
    fun providesSchedulerCoupler(): SchedulersCoupler = SchedulersCouplerImpl()

}