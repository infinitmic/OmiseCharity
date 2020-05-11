package com.omise.charity.view.charity.fragments.di

import com.omise.charity.application.di.FragmentScope
import com.omise.charity.view.charity.fragments.CharityListFragment
import com.omise.charity.view.charity.fragments.CharityListFragmentView
import dagger.Module
import dagger.Provides

@Module
class CharityListFragmentModule {

    @FragmentScope
    @Provides
    fun providesHeadlinesFragmentView(headlinesListFragment: CharityListFragment): CharityListFragmentView {
        return headlinesListFragment
    }
}