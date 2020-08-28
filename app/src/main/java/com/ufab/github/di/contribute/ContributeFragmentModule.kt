package com.ufab.github.di.contribute

import com.ufab.github.di.FragmentScope
import com.ufab.github.ui.contributorlist.ContributorFragment
import com.ufab.github.ui.contributorlist.ContributorModule
import com.ufab.github.ui.dashboard.homefragment.HomeFragment
import com.ufab.github.ui.dashboard.homefragment.HomeFragmentModule
import com.ufab.github.ui.reposotries.ReposotriesFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributeFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun contributeHomeFragment(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ContributorModule::class])
    abstract fun contributeorFragment(): ContributorFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ContributorModule::class])
    abstract fun repositoriesFragment(): ReposotriesFragment




}