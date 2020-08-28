package com.ufab.github.ui.contributorlist

import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso
import com.ufab.github.di.FragmentScope
import com.ufab.github.di.ViewModelKey
import com.ufab.github.di.module.CompositeModule
import com.ufab.github.di.module.RepositoryModule
import com.ufab.github.di.module.SchedulerModule
import com.ufab.github.global.utils.ExtraKeys
import com.ufab.github.ui.contributorlist.adapter.ContributorAdapter
import com.ufab.github.ui.dashboard.homefragment.HomeFragmentViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module(includes = [RepositoryModule::class, CompositeModule::class, SchedulerModule::class])

class ContributorModule {
    @IntoMap
    @Provides
    @ViewModelKey(ContributorViewModel::class)
    fun bindHomeViewModel(contributeFragmentViewModel: ContributorViewModel): ViewModel {
        return contributeFragmentViewModel
    }

    @Provides
    @FragmentScope
    fun provideContributorAdapter(picasso: Picasso, viewModel: ContributorViewModel): ContributorAdapter {
        return ContributorAdapter(picasso,viewModel)
    }

}