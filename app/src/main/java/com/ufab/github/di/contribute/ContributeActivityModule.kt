package com.ufab.github.di.contribute

import com.ufab.github.di.ActivityScope
import com.ufab.github.ui.dashboard.DashboardActivity
import com.ufab.github.ui.dashboard.DashboardModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributeActivityModule {




    @ActivityScope
    @ContributesAndroidInjector(modules = [DashboardModule::class,ContributeFragmentModule::class])
    abstract fun contributeHomeActivity(): DashboardActivity






}
