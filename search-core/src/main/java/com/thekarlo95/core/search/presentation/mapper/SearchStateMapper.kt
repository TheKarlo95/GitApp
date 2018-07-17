package com.thekarlo95.core.search.presentation.mapper

import com.thekarlo95.core.search.domain.model.SearchDomainState
import com.thekarlo95.core.search.presentation.model.SearchPresentationState
import javax.inject.Inject

class SearchStateMapper @Inject constructor(
        private val repositoryMapper: RepositoryPresentationMapper
) : PresentationMapper<SearchDomainState, SearchPresentationState> {

    override fun toPresentation(domainModel: SearchDomainState): SearchPresentationState {
        return when (domainModel) {
            is SearchDomainState.Idle -> SearchPresentationState.Idle
            is SearchDomainState.Loading -> SearchPresentationState.Loading
            is SearchDomainState.Complete ->
                SearchPresentationState.Complete(repositoryMapper.toPresentaitonList(domainModel.payload))
            is SearchDomainState.Error ->
                SearchPresentationState.Error(domainModel.payload)
        }
    }
}