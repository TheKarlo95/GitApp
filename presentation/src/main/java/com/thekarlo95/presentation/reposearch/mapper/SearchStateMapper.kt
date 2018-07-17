package com.thekarlo95.presentation.reposearch.mapper

import com.thekarlo95.domain.reposearch.model.SearchDomainState
import com.thekarlo95.presentation.core.mapper.PresentationMapper
import com.thekarlo95.presentation.core.mapper.RepositoryPresentationMapper
import com.thekarlo95.presentation.reposearch.model.SearchPresentationState
import javax.inject.Inject

class SearchStateMapper @Inject constructor(
        private val repositoryMapper: RepositoryPresentationMapper
) : PresentationMapper<SearchDomainState, SearchPresentationState> {

    override fun toPresentation(domainModel: SearchDomainState): SearchPresentationState {
        return when (domainModel) {
            is SearchDomainState.Idle -> SearchPresentationState.Idle
            is SearchDomainState.Loading -> SearchPresentationState.Loading
            is SearchDomainState.Complete ->
                SearchPresentationState.Complete(repositoryMapper.toPresentationList(domainModel.payload))
            is SearchDomainState.Error ->
                SearchPresentationState.Error(domainModel.payload)
        }
    }
}