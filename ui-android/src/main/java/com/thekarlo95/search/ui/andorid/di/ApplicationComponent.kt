package com.thekarlo95.search.ui.andorid.di

import android.app.Application
import com.thekarlo95.search.ui.andorid.AndroidApplication
import com.thekarlo95.search.ui.andorid.di.module.ActivityBindingModule
import com.thekarlo95.search.ui.andorid.di.module.ApplicationModule
import com.thekarlo95.search.ui.andorid.di.scope.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
        modules = [
            ActivityBindingModule::class,
            ApplicationModule::class,
            AndroidSupportInjectionModule::class
        ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: AndroidApplication)
}