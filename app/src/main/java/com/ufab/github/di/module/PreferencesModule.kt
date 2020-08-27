package com.ufab.github.di.module


import android.content.Context
import com.ufab.github.di.ApplicationContext
import com.ufab.github.di.ApplicationScope
import com.ufab.github.global.helper.SharedPreferences
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides

@Module(includes = [ApplicationModule::class, ParsingModule::class])
class PreferencesModule {

    @Provides
    @ApplicationScope
    fun sharedPreferences(@ApplicationContext context: Context, moshi: Moshi): SharedPreferences {
        return SharedPreferences(context, moshi)
    }
}
