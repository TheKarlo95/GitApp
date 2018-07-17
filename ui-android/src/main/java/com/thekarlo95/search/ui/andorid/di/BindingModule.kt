package com.thekarlo95.search.ui.andorid.di

import com.thekarlo95.search.ui.andorid.MainActivity
import com.thekarlo95.search.ui.andorid.di.module.MainActivityModule
import com.thekarlo95.search.ui.andorid.di.module.RepositoryDetailModule
import com.thekarlo95.search.ui.andorid.di.module.RepositorySearchModule
import com.thekarlo95.search.ui.andorid.di.module.UserModule
import com.thekarlo95.search.ui.andorid.di.scope.ActivityScope
import com.thekarlo95.search.ui.andorid.di.scope.FragmentScope
import com.thekarlo95.search.ui.andorid.repositorydetail.RepositoryDetailFragment
import com.thekarlo95.search.ui.andorid.respositorysearch.RepositorySearchFragment
import com.thekarlo95.search.ui.andorid.user.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributesMainActivity(): MainActivity

    @FragmentScope
    @ContributesAndroidInjector(modules = [RepositoryDetailModule::class])
    abstract fun contributesRepositoryDetailFragment(): RepositoryDetailFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [RepositorySearchModule::class])
    abstract fun contributesRepositorySearchFragment(): RepositorySearchFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [UserModule::class])
    abstract fun contributesUserFragment(): UserFragment
}