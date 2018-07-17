package com.thekarlo95.search.ui.andorid.di.module

import com.thekarlo95.domain.user.interactor.GetUserInteractor
import com.thekarlo95.presentation.user.mapper.UserStateMapper
import com.thekarlo95.presentation.user.presenter.UserDefaultPresenter
import com.thekarlo95.presentation.user.presenter.UserPresenter
import com.thekarlo95.search.ui.andorid.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @FragmentScope
    @Provides
    fun provideUserDefaultPresenter(
            userInteractor: GetUserInteractor,
            mapper: UserStateMapper
    ): UserPresenter {
        return UserDefaultPresenter(userInteractor, mapper)
    }
}