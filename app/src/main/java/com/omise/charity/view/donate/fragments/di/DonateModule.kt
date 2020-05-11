package com.omise.charity.view.donate.fragments.di


import com.omise.charity.application.di.FragmentScope
import com.omise.charity.util.common.ResourcesValueProvider
import com.omise.charity.view.donate.fragments.DonateFragment
import com.omise.charity.view.donate.fragments.DonateFragmentView
import com.omise.charity.view.donate.fragments.helper.DonateErrorMessage
import com.omise.charity.view.donate.fragments.helper.DonateErrorMessageImpl
import com.omise.charity.view.donate.fragments.helper.DonateUIValidator
import com.omise.charity.view.donate.fragments.helper.DonateUIValidatorImpl
import dagger.Module
import dagger.Provides

@Module
class DonateModule {

    @FragmentScope
    @Provides
    fun providesDomainFragmentView(donateFragment: DonateFragment): DonateFragmentView {
        return donateFragment
    }

    @FragmentScope
    @Provides
    fun providesDomainUIErrorMessage(resourcesValueProvider: ResourcesValueProvider):
            DonateErrorMessage = DonateErrorMessageImpl(resourcesValueProvider)

    @Provides
    @FragmentScope
    fun providesDonateUIValidator(): DonateUIValidator = DonateUIValidatorImpl()
}