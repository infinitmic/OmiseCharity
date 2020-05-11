package com.omise.charity.view.charity.di

import com.omise.charity.application.di.FragmentScope
import com.omise.charity.view.charity.fragments.CharityListFragment
import com.omise.charity.view.charity.fragments.di.CharityListFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CharityListFragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(CharityListFragmentModule::class))
    abstract fun contributesHeadlinesFragment(): CharityListFragment

}