package com.ufab.github.di.module

import android.app.Application
import android.content.Context
import com.ufab.github.di.ApplicationContext
import com.ufab.github.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module(includes = [APIClientModule::class, PicassoModule::class, PreferencesModule::class, DatabaseModule::class])
class ApplicationModule() {

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun context(application: Application): Context {
        return application.applicationContext
    }
}
