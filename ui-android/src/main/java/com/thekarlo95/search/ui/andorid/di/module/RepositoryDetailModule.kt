package com.thekarlo95.search.ui.andorid.di.module

import com.thekarlo95.domain.repodetail.interactor.GetRepositoryDetailInteractor
import com.thekarlo95.presentation.repodetail.mapper.RepositoryDetailStateMapper
import com.thekarlo95.presentation.repodetail.presenter.RepositoryDetailDefaultPresenter
import com.thekarlo95.presentation.repodetail.presenter.RepositoryDetailPresenter
import com.thekarlo95.search.ui.andorid.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class RepositoryDetailModule {

    @FragmentScope
    @Provides
    fun providePresenter(
            repositoryDetailInteractor: GetRepositoryDetailInteractor,
            mapper: RepositoryDetailStateMapper
    ): RepositoryDetailPresenter {
        return RepositoryDetailDefaultPresenter(repositoryDetailInteractor, mapper)
    }
}