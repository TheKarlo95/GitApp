package com.thekarlo95.search.ui.andorid.di.module

import com.thekarlo95.search.ui.andorid.respositorysearch.RepositorySearchActivity
import com.thekarlo95.search.ui.andorid.di.scope.PerActivity
import com.thekarlo95.search.ui.andorid.repositorydetail.RepositoryDetailActivity
import com.thekarlo95.search.ui.andorid.user.UserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [(RepositorySearchActivityModule::class)])
    abstract fun bindRepositorySearchActivity(): RepositorySearchActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [(RepositoryDetailActivityModule::class)])
    abstract fun bindRepositoryDetailActivity(): RepositoryDetailActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [(UserActivityModule::class)])
    abstract fun bindUserActivity(): UserActivity
}