package com.ufab.github.di.module


import com.ufab.github.data.repository.abs.HomeRepository
import com.ufab.github.data.repository.abs.NewsRepository
import com.ufab.github.data.repository.abs.TimeRepository
import com.ufab.github.data.repository.abs.UserRepository
import com.ufab.github.data.repository.imp.HomeRepositoryImp
import com.ufab.github.data.repository.imp.NewsRepositoryImp
import com.ufab.github.data.repository.imp.TimeRepositoryImp
import com.ufab.github.data.repository.imp.UserRepositoryImp
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideProfileRepository(profileImp: UserRepositoryImp): UserRepository

    @Binds
    abstract fun provideTimeRepository(timerImp: TimeRepositoryImp): TimeRepository

    @Binds
    abstract fun provideNewsRepository(newsImp: NewsRepositoryImp): NewsRepository

    @Binds
    abstract fun ProvideHomeRepository(homeImp:HomeRepositoryImp):HomeRepository

}
