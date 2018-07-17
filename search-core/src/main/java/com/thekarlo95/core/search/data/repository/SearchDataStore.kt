package com.thekarlo95.core.search.data.repository

import com.thekarlo95.core.search.data.model.OrderParamDataModel
import com.thekarlo95.core.search.data.model.SearchDataState
import io.reactivex.Flowable

interface SearchDataStore {
    fun searchRepositories(
            search: String,
            page: Int = 1,
            order: OrderParamDataModel = OrderParamDataModel.STARS,
            perPage: Int = 10
    ): Flowable<SearchDataState>
}