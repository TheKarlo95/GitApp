package com.thekarlo95.search.ui.andorid.di.module

import com.thekarlo95.domain.reposearch.interactor.SearchInteractor
import com.thekarlo95.domain.user.interactor.GetUserInteractor
import com.thekarlo95.presentation.core.PresentationLogger
import com.thekarlo95.presentation.reposearch.mapper.SearchStateMapper
import com.thekarlo95.presentation.reposearch.presenter.SearchDefaultPresenter
import com.thekarlo95.presentation.reposearch.presenter.SearchPresenter
import com.thekarlo95.presentation.user.mapper.UserStateMapper
import com.thekarlo95.presentation.user.presenter.UserDefaultPresenter
import com.thekarlo95.presentation.user.presenter.UserPresenter
import dagger.Module
import dagger.Provides

@Module
open class UserActivityModule {

    @Provides
    fun provideBrowseBufferoosViewModelFactory(
            userInteractor: GetUserInteractor,
            mapper: UserStateMapper
    ): UserPresenter {
        return UserDefaultPresenter(userInteractor, mapper)
    }
}