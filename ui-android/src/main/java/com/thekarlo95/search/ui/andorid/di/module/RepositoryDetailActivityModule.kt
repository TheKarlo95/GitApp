package com.thekarlo95.search.ui.andorid.di.module

import com.thekarlo95.domain.repodetail.interactor.GetRepositoryDetailInteractor
import com.thekarlo95.presentation.repodetail.mapper.RepositoryDetailStateMapper
import com.thekarlo95.presentation.repodetail.presenter.RepositoryDetailDefaultPresenter
import com.thekarlo95.presentation.repodetail.presenter.RepositoryDetailPresenter
import dagger.Module
import dagger.Provides

@Module
open class RepositoryDetailActivityModule {

    @Provides
    fun provideBrowseBufferoosViewModelFactory(
            repositoryDetailInteractor: GetRepositoryDetailInteractor,
            mapper: RepositoryDetailStateMapper
    ): RepositoryDetailPresenter {
        return RepositoryDetailDefaultPresenter(repositoryDetailInteractor, mapper)
    }
}