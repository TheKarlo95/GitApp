package com.thekarlo95.search.ui.andorid.di.component

import com.thekarlo95.search.ui.andorid.respositorysearch.RepositorySearchActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface RepositorySearchSubComponent : AndroidInjector<RepositorySearchActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<RepositorySearchActivity>()
}