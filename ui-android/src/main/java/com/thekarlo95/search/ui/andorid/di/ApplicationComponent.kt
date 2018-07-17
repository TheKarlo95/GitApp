package com.thekarlo95.search.ui.andorid.di

import com.thekarlo95.search.ui.andorid.AndroidApplication
import com.thekarlo95.search.ui.andorid.di.module.ApplicationModule
import com.thekarlo95.search.ui.andorid.di.scope.ApplicationScope
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    BindingModule::class,
    ApplicationModule::class
])
interface ApplicationComponent : AndroidInjector<AndroidApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AndroidApplication>()
}