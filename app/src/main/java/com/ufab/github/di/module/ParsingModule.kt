package com.ufab.github.di.module

import com.ufab.github.di.ApplicationScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides

@Module
class ParsingModule {


    @Provides
    @ApplicationScope
    fun moshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }
}