package com.thekarlo95.presentation.reposearch.presenter

import com.thekarlo95.domain.core.model.OrderParamDomainModel
import com.thekarlo95.domain.reposearch.interactor.SearchInteractor
import com.thekarlo95.presentation.core.model.OrderParamPresentationModel
import com.thekarlo95.presentation.reposearch.view.SearchNoopView
import com.thekarlo95.presentation.reposearch.view.SearchView
import javax.inject.Inject

class MockSearchPresenter @Inject constructor() : SearchPresenter {

    override var view: SearchView = SearchNoopView
    var searchParams: SearchInteractor.SearchParams = SearchInteractor.SearchParams("")

    override fun initialize() {
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
        view = SearchNoopView
    }

    override fun onSearchTextChange(searchParameter: String) {
        view.clearList()
        searchParams = SearchInteractor.SearchParams(searchParameter)
    }

    override fun loadPage(page: Int) {
        searchParams = searchParams.copy(page = page)
    }

    override fun sortBy(orderParam: OrderParamPresentationModel) {
        searchParams = searchParams.copy(page = 1, order = orderParam.toDomain())
    }

    private fun OrderParamPresentationModel.toDomain(): OrderParamDomainModel = when(this) {
        OrderParamPresentationModel.FORKS -> OrderParamDomainModel.FORKS
        OrderParamPresentationModel.STARS -> OrderParamDomainModel.STARS
        OrderParamPresentationModel.UPDATED -> OrderParamDomainModel.UPDATED
    }
}