package com.ufab.github.di.module


import android.content.Context
import com.ufab.github.di.ApplicationContext
import com.ufab.github.di.ApplicationScope
import com.ufab.github.data.db.Database
import com.ufab.github.data.db.DatabaseBuilder
import dagger.Module
import dagger.Provides

@Module(includes = [ApplicationModule::class])
class DatabaseModule {

    @Provides
    @ApplicationScope
    fun databaseProvider(@ApplicationContext context: Context): Database {
        return DatabaseBuilder.getBingoDatabase(context)
    }
}
