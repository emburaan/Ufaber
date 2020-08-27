package com.ufab.github.di.module

import android.content.Context
import com.ufab.github.di.ApplicationContext
import com.ufab.github.di.ApplicationScope
import com.ufab.github.di.PicassoClientScope
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module(includes = [ApplicationModule::class, NetworkModule::class])
class PicassoModule {

    @Provides
    @ApplicationScope
    fun picasso(@ApplicationContext context: Context, okHttp3Downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(context).loggingEnabled(true).downloader(okHttp3Downloader).build()
    }

    @Provides
    @ApplicationScope
    fun okHttp3Downloader(@PicassoClientScope okHttpClient: OkHttpClient): OkHttp3Downloader {
        return OkHttp3Downloader(okHttpClient)
    }

}
