package com.omise.charity.application.di

import com.omise.charity.view.charity.di.CharityListFragmentBuilder
import com.omise.charity.view.charity.screen.CharityListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(CharityListFragmentBuilder::class))
    abstract fun contributesCharityListActivity(): CharityListActivity
}