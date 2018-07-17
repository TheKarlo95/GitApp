package com.thekarlo95.core.search.presentation.presenter

import com.thekarlo95.core.search.domain.interactor.DefaultObserver
import com.thekarlo95.core.search.domain.interactor.SearchInteractor
import com.thekarlo95.core.search.domain.model.SearchDomainState
import com.thekarlo95.core.search.presentation.NoopView
import com.thekarlo95.core.search.presentation.SearchView
import com.thekarlo95.core.search.presentation.mapper.SearchStateMapper
import com.thekarlo95.core.search.presentation.model.OrderParamPresentationModel
import com.thekarlo95.core.search.presentation.model.SearchPresentationState
import javax.inject.Inject

class SearchDefaultPresenter @Inject constructor(
        private val searchInteractor: SearchInteractor,
        private val mapper: SearchStateMapper
) : SearchPresenter {

    override var view: SearchView = NoopView
    var searchParams: SearchInteractor.SearchParams = SearchInteractor.SearchParams("")

    override fun initialize() {
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
        searchInteractor.dispose()
        view = NoopView
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
        searchParams = searchParams.copy(page = 1, order = orderParam)
        if (searchParams.search.isNotBlank()) {
            searchInteractor.execute(SearchObserver(view, mapper), searchParams)
        }
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