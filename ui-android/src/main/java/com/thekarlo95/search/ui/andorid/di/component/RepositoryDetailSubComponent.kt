package com.thekarlo95.search.ui.andorid.di.component

import com.thekarlo95.search.ui.andorid.repositorydetail.RepositoryDetailActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface RepositoryDetailSubComponent : AndroidInjector<RepositoryDetailActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<RepositoryDetailActivity>()
}