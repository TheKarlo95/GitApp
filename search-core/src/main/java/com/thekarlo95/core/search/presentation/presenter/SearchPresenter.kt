package com.thekarlo95.core.search.presentation.presenter

import com.thekarlo95.core.search.presentation.SearchView
import com.thekarlo95.core.search.presentation.model.OrderParamPresentationModel

interface SearchPresenter {

    var view: SearchView

    fun initialize()

    fun resume()

    fun pause()

    fun destroy()

    fun onSearchTextChange(searchParameter: String)

    fun loadPage(page: Int)

    fun sortBy(orderParam: OrderParamPresentationModel)
}