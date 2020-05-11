package com.omise.charity.application

import com.omise.charity.application.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class OmiseCharityApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}