package com.ufab.github.ui.reposotries

import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso
import com.ufab.github.di.FragmentScope
import com.ufab.github.di.ViewModelKey
import com.ufab.github.di.module.CompositeModule
import com.ufab.github.di.module.RepositoryModule
import com.ufab.github.di.module.SchedulerModule
import com.ufab.github.ui.contributorlist.ContributorViewModel
import com.ufab.github.ui.contributorlist.adapter.ContributorAdapter
import com.ufab.github.ui.reposotries.adapter.RepositoriesAdapter
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [RepositoryModule::class, CompositeModule::class, SchedulerModule::class])
class RepositoriesModule {

    @IntoMap
    @Provides
    @ViewModelKey(RepositoriesFragmentViewModel::class)
    fun bindHomeViewModel(repositoriesFragmentViewModel: RepositoriesFragmentViewModel): ViewModel {
        return repositoriesFragmentViewModel
    }

    @Provides
    @FragmentScope
    fun provideRepositoriesAdapter(picasso: Picasso, viewModel: RepositoriesFragmentViewModel): RepositoriesAdapter {
        return RepositoriesAdapter(picasso,viewModel)
    }
}