package com.thekarlo95.core.search.domain.repository

import com.thekarlo95.core.search.domain.model.OrderParamDomainModel
import com.thekarlo95.core.search.domain.model.SearchDomainState
import io.reactivex.Flowable

interface SearchRepository {

    fun searchRepositories(
            search: String,
            page: Int,
            order: OrderParamDomainModel,
            perPage: Int
    ): Flowable<SearchDomainState>
}