package com.thekarlo95.search.ui.andorid.di.module

import com.thekarlo95.domain.reposearch.interactor.SearchInteractor
import com.thekarlo95.presentation.reposearch.mapper.SearchStateMapper
import com.thekarlo95.presentation.reposearch.presenter.SearchDefaultPresenter
import com.thekarlo95.presentation.reposearch.presenter.SearchPresenter
import dagger.Module
import dagger.Provides

@Module
open class RepositorySearchActivityModule {

    @Provides
    fun provideBrowseBufferoosViewModelFactory(
            searchInteractor: SearchInteractor,
            mapper: SearchStateMapper
    ): SearchPresenter {
        return SearchDefaultPresenter(searchInteractor, mapper)
    }
}