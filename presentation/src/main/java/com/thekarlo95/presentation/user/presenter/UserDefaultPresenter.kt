package com.thekarlo95.presentation.user.presenter

import com.thekarlo95.domain.core.interactor.DefaultObserver
import com.thekarlo95.domain.user.interactor.GetUserInteractor
import com.thekarlo95.domain.user.model.UserDomainState
import com.thekarlo95.presentation.core.PresentationLogger
import com.thekarlo95.presentation.user.mapper.UserStateMapper
import com.thekarlo95.presentation.user.model.UserPresentationState
import com.thekarlo95.presentation.user.view.UserNoopView
import com.thekarlo95.presentation.user.view.UserView
import javax.inject.Inject

class UserDefaultPresenter @Inject constructor(
        private val userInteractor: GetUserInteractor,
        private val mapper: UserStateMapper
) : UserPresenter {

    override var view: UserView = UserNoopView

    override fun initialize() {}

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        userInteractor.dispose()
        view = UserNoopView
    }

    override fun loadUser(username: String) {
        if (username.isNotBlank()) {
            val params = GetUserInteractor.Params(username)
            userInteractor.execute(RepositoryDetailObserver(view, mapper), params)
        }
    }

    class RepositoryDetailObserver(
            private val view: UserView,
            private val mapper: UserStateMapper
    ) : DefaultObserver<UserDomainState>() {

        override fun onNext(state: UserDomainState) {
            with(mapper.toPresentation(state)) {
                when (this) {
                    is UserPresentationState.Idle -> view.hideLoading()
                    is UserPresentationState.Loading -> view.showLoading()
                    is UserPresentationState.Complete -> {
                        view.hideLoading()
                        view.showUser(payload)
                    }
                    is UserPresentationState.Error -> {
                        view.hideLoading()
                        view.showError(payload.toString())
                    }
                }
            }
        }
    }
}