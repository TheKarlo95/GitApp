package com.thekarlo95.search.ui.andorid.di.module

import com.thekarlo95.domain.reposearch.interactor.SearchInteractor
import com.thekarlo95.presentation.reposearch.mapper.SearchStateMapper
import com.thekarlo95.presentation.reposearch.presenter.SearchDefaultPresenter
import com.thekarlo95.presentation.reposearch.presenter.SearchPresenter
import com.thekarlo95.search.ui.andorid.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class RepositorySearchModule {

    @FragmentScope
    @Provides
    fun providePresenter(
            searchInteractor: SearchInteractor,
            mapper: SearchStateMapper
    ): SearchPresenter {
        return SearchDefaultPresenter(searchInteractor, mapper)
    }
}