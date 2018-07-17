package com.thekarlo95.presentation.repodetail.presenter

import com.thekarlo95.domain.core.interactor.DefaultObserver
import com.thekarlo95.domain.repodetail.interactor.GetRepositoryDetailInteractor
import com.thekarlo95.domain.repodetail.model.RepositoryDetailDomainState
import com.thekarlo95.presentation.repodetail.mapper.RepositoryDetailStateMapper
import com.thekarlo95.presentation.repodetail.model.RepositoryDetailPresentationState
import com.thekarlo95.presentation.repodetail.view.RepositoryDetailNoopView
import com.thekarlo95.presentation.repodetail.view.RepositoryDetailView
import javax.inject.Inject

class RepositoryDetailDefaultPresenter @Inject constructor(
        private val repositoryDetailInteractor: GetRepositoryDetailInteractor,
        private val mapper: RepositoryDetailStateMapper
) : RepositoryDetailPresenter {

    override var view: RepositoryDetailView = RepositoryDetailNoopView

    override fun initialize() {
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
        repositoryDetailInteractor.dispose()
        view = RepositoryDetailNoopView
    }

    override fun loadRepository(ownerName: String, repositoryName: String) {
        if (ownerName.isNotBlank() && repositoryName.isNotBlank()) {
            val params = GetRepositoryDetailInteractor.Params(ownerName, repositoryName)
            repositoryDetailInteractor.execute(RepositoryDetailObserver(view, mapper), params)
        }
    }

    class RepositoryDetailObserver(
            private val view: RepositoryDetailView,
            private val mapper: RepositoryDetailStateMapper
    ) : DefaultObserver<RepositoryDetailDomainState>() {

        override fun onNext(state: RepositoryDetailDomainState) {
            with(mapper.toPresentation(state)) {
                when (this) {
                    is RepositoryDetailPresentationState.Idle -> view.hideLoading()
                    is RepositoryDetailPresentationState.Loading -> view.showLoading()
                    is RepositoryDetailPresentationState.Complete -> {
                        view.hideLoading()
                        view.showRepository(payload)
                    }
                    is RepositoryDetailPresentationState.Error -> {
                        view.hideLoading()
                        view.showError(payload.toString())
                    }
                }
            }
        }
    }
}