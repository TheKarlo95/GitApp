package com.thekarlo95.core.search.data.mapper

import com.thekarlo95.core.search.data.model.SearchDataState
import com.thekarlo95.core.search.domain.model.SearchDomainState
import javax.inject.Inject

class SearchStateMapper @Inject constructor(
        private val repositoryMapper: RepositoryDataMapper
) : DataMapper<SearchDataState, SearchDomainState> {

    override fun toDomain(dataModel: SearchDataState): SearchDomainState {
        return when (dataModel) {
            is SearchDataState.Idle -> SearchDomainState.Idle
            is SearchDataState.Loading -> SearchDomainState.Loading
            is SearchDataState.Complete ->
                SearchDomainState.Complete(repositoryMapper.toDomainList(dataModel.payload))
            is SearchDataState.Error ->
                SearchDomainState.Error(dataModel.payload)
        }
    }

    override fun toData(domainModel: SearchDomainState): SearchDataState {
        return when (domainModel) {
            is SearchDomainState.Idle -> SearchDataState.Idle
            is SearchDomainState.Loading -> SearchDataState.Loading
            is SearchDomainState.Complete ->
                SearchDataState.Complete(repositoryMapper.toDataList(domainModel.payload))
            is SearchDomainState.Error ->
                SearchDataState.Error(domainModel.payload)
        }
    }
}