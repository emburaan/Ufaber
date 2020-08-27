package com.ufab.github.di.module

import com.ufab.github.global.helper.AppSchedulerProvider
import com.ufab.github.global.listener.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class SchedulerModule {

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}
