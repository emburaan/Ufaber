package com.ufab.github.ui.dashboard.homefragment

import androidx.lifecycle.ViewModel
import com.ufab.github.di.FragmentScope
import com.ufab.github.di.ViewModelKey
import com.ufab.github.di.module.CompositeModule
import com.ufab.github.di.module.RepositoryModule
import com.ufab.github.di.module.SchedulerModule
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module(includes = [RepositoryModule::class, CompositeModule::class, SchedulerModule::class])
class HomeFragmentModule {

    @IntoMap
    @Provides
    @ViewModelKey(HomeFragmentViewModel::class)
    fun bindHomeViewModel(homeFragmentViewModel: HomeFragmentViewModel): ViewModel {
        return homeFragmentViewModel
    }

  /*  @Provides
    @FragmentScope
    fun provideHomeCachedAdapter(picasso: Picasso):HomeAdapter{
        return HomeAdapter(picasso)
    }*/

    @Provides
    @FragmentScope
    fun provideHomeCachedAdapter2(picasso: Picasso,viewModel: HomeFragmentViewModel):HomeAdapter2{
        return HomeAdapter2(picasso,viewModel)
    }
}