package com.ufab.github.ui.contributorlist

import com.ufab.github.di.module.CompositeModule
import com.ufab.github.di.module.RepositoryModule
import com.ufab.github.di.module.SchedulerModule
import dagger.Module

@Module(includes = [RepositoryModule::class, CompositeModule::class, SchedulerModule::class])

class ContributorModule {
}