package com.thekarlo95.presentation.reposearch.presenter

import com.thekarlo95.domain.core.interactor.DefaultObserver
import com.thekarlo95.domain.core.model.OrderParamDomainModel
import com.thekarlo95.domain.reposearch.interactor.SearchInteractor
import com.thekarlo95.domain.reposearch.model.SearchDomainState
import com.thekarlo95.presentation.core.model.OrderParamPresentationModel
import com.thekarlo95.presentation.reposearch.mapper.SearchStateMapper
import com.thekarlo95.presentation.reposearch.model.SearchPresentationState
import com.thekarlo95.presentation.reposearch.view.SearchNoopView
import com.thekarlo95.presentation.reposearch.view.SearchView
import javax.inject.Inject

class SearchDefaultPresenter @Inject constructor(
        private val searchInteractor: SearchInteractor,
        private val mapper: SearchStateMapper
) : SearchPresenter {

    override var view: SearchView = SearchNoopView
    var searchParams: SearchInteractor.SearchParams = SearchInteractor.SearchParams("")

    override fun initialize() {
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
        searchInteractor.dispose()
        view = SearchNoopView
    }

    override fun onSearchTextChange(searchParameter: String) {
        if (searchParameter.isNotBlank()) {
            searchParams = SearchInteractor.SearchParams(searchParameter)
            searchInteractor.execute(SearchObserver(view, mapper, view::clearList), searchParams)
        } else {
            view.clearList()
        }
    }

    override fun loadPage(page: Int) {
        searchParams = searchParams.copy(page = page)
        searchInteractor.execute(SearchObserver(view, mapper), searchParams)
    }

    override fun sortBy(orderParam: OrderParamPresentationModel) {
        searchParams = searchParams.copy(page = 1, order = orderParam.toDomain())
        if (searchParams.search.isNotBlank()) {
            searchInteractor.execute(SearchObserver(view, mapper), searchParams)
        }
    }

    private fun OrderParamPresentationModel.toDomain(): OrderParamDomainModel = when(this) {
        OrderParamPresentationModel.FORKS -> OrderParamDomainModel.FORKS
        OrderParamPresentationModel.STARS -> OrderParamDomainModel.STARS
        OrderParamPresentationModel.UPDATED -> OrderParamDomainModel.UPDATED
    }

    class SearchObserver(
            private val view: SearchView,
            private val mapper: SearchStateMapper,
            private val onFirstOnNext: () -> Unit = {}
    ) : DefaultObserver<SearchDomainState>() {

        override fun onNext(state: SearchDomainState) {
            with(mapper.toPresentation(state)) {
                when (this) {
                    is SearchPresentationState.Idle -> view.hideLoading()
                    is SearchPresentationState.Loading -> view.showLoading()
                    is SearchPresentationState.Complete -> {
                        view.hideLoading()
                        view.addToRepositoryList(payload)
                    }
                    is SearchPresentationState.Error -> {
                        view.hideLoading()
                        view.showError(payload.toString())
                    }
                }
            }
        }

        override fun onFirstOnNext(state: SearchDomainState) {
            onFirstOnNext.invoke()
        }
    }
}