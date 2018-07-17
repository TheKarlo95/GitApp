package com.thekarlo95.presentation.reposearch.presenter

import com.thekarlo95.presentation.core.model.OrderParamPresentationModel
import com.thekarlo95.presentation.core.presenter.Presenter
import com.thekarlo95.presentation.reposearch.view.SearchView

interface SearchPresenter : Presenter {

    var view: SearchView

    fun onSearchTextChange(searchParameter: String)

    fun loadPage(page: Int)

    fun sortBy(orderParam: OrderParamPresentationModel)
}