package com.ufab.github.di.component


import android.app.Application
import com.ufab.github.MVVMApplication
import com.ufab.github.di.ApplicationScope
import com.ufab.github.di.contribute.ContributeActivityModule
import com.ufab.github.di.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule


@ApplicationScope
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class, ContributeActivityModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance

        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MVVMApplication)

}
