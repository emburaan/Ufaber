package com.ufab.github.di.contribute

import com.ufab.github.di.FragmentScope
import com.ufab.github.ui.dashboard.homefragment.HomeFragment
import com.ufab.github.ui.dashboard.homefragment.HomeFragmentModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributeFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun contributeHomeFragment(): HomeFragment




}