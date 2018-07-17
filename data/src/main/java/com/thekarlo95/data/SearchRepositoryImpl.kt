package com.thekarlo95.data

import com.thekarlo95.data.datastore.RepositoryDataStore
import com.thekarlo95.data.mapper.SearchStateMapper
import com.thekarlo95.data.model.OrderParamDataModel
import com.thekarlo95.domain.core.model.OrderParamDomainModel
import com.thekarlo95.domain.reposearch.model.SearchDomainState
import com.thekarlo95.domain.reposearch.repository.SearchRepository
import io.reactivex.Flowable
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
        private val dataStore: RepositoryDataStore,
        private val mapper: SearchStateMapper
) : SearchRepository {

    override fun searchRepositories(
            search: String,
            page: Int,
            order: OrderParamDomainModel,
            perPage: Int
    ): Flowable<SearchDomainState> {
        return dataStore.searchRepositories(search, page, order.toData(), perPage)
                .map { mapper.toDomain(it) }
                .startWith(SearchDomainState.Loading)
                .onErrorReturn { SearchDomainState.Error(it) }
    }

    private fun OrderParamDomainModel.toData(): OrderParamDataModel = when (this) {
        OrderParamDomainModel.FORKS -> OrderParamDataModel.FORKS
        OrderParamDomainModel.STARS -> OrderParamDataModel.STARS
        OrderParamDomainModel.UPDATED -> OrderParamDataModel.UPDATED
    }
}