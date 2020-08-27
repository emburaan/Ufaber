package com.ufab.github.ui.dashboard

import androidx.lifecycle.ViewModel
import com.ufab.github.di.ViewModelKey
import com.ufab.github.di.module.CompositeModule
import com.ufab.github.di.module.RepositoryModule
import com.ufab.github.di.module.SchedulerModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module(includes = [RepositoryModule::class, CompositeModule::class, SchedulerModule::class])
class DashboardModule {


    @IntoMap
    @Provides
    @ViewModelKey(DashboardViewModel::class)
    fun bindHomeViewModel(dashboardViewModel: DashboardViewModel): ViewModel {
        return dashboardViewModel
    }


  /*  @Provides
    @Named(ExtraKeys.HomeActivity.HOME_INJECT_USER_KEY)
    fun provideUser(mainActivity: HomeActivity): User {
        return mainActivity.intent.getParcelableExtra(ExtraKeys.HomeActivity.HOME_EXTRA_USER_KEY)
    }*/
}
