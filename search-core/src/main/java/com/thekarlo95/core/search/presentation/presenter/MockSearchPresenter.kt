package com.thekarlo95.core.search.presentation.presenter

import com.thekarlo95.core.search.domain.interactor.SearchInteractor
import com.thekarlo95.core.search.presentation.NoopView
import com.thekarlo95.core.search.presentation.SearchView
import com.thekarlo95.core.search.presentation.model.OrderParamPresentationModel
import javax.inject.Inject

class MockSearchPresenter @Inject constructor() : SearchPresenter {

    override var view: SearchView = NoopView
    var searchParams: SearchInteractor.SearchParams = SearchInteractor.SearchParams("")

    override fun initialize() {
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
        view = NoopView
    }

    override fun onSearchTextChange(searchParameter: String) {
        view.clearList()
        searchParams = SearchInteractor.SearchParams(searchParameter)
    }

    override fun loadPage(page: Int) {
        searchParams = searchParams.copy(page = page)
    }

    override fun sortBy(orderParam: OrderParamPresentationModel) {
        searchParams = searchParams.copy(page = 1, order = orderParam)
    }
}