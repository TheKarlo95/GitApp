package com.thekarlo95.domain.reposearch.repository

import com.thekarlo95.domain.core.model.OrderParamDomainModel
import com.thekarlo95.domain.reposearch.model.SearchDomainState
import io.reactivex.Flowable

interface SearchRepository {

    fun searchRepositories(
            search: String,
            page: Int,
            order: OrderParamDomainModel,
            perPage: Int
    ): Flowable<SearchDomainState>
}