package com.omise.charity.view.donate.fragments.di

import com.omise.charity.application.di.FragmentScope
import com.omise.charity.view.donate.fragments.DonateFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DonateFragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(DonateModule::class))
    abstract fun contributesDonateFragment(): DonateFragment

}