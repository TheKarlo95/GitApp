package com.thekarlo95.presentation.repodetail.mapper

import com.thekarlo95.domain.repodetail.model.RepositoryDetailDomainState
import com.thekarlo95.presentation.core.mapper.PresentationMapper
import com.thekarlo95.presentation.core.mapper.RepositoryPresentationMapper
import com.thekarlo95.presentation.repodetail.model.RepositoryDetailPresentationState
import javax.inject.Inject

class RepositoryDetailStateMapper @Inject constructor(
        private val repositoryMapper: RepositoryPresentationMapper
) : PresentationMapper<RepositoryDetailDomainState, RepositoryDetailPresentationState> {

    override fun toPresentation(domainModel: RepositoryDetailDomainState): RepositoryDetailPresentationState {
        return when (domainModel) {
            is RepositoryDetailDomainState.Idle -> RepositoryDetailPresentationState.Idle
            is RepositoryDetailDomainState.Loading -> RepositoryDetailPresentationState.Loading
            is RepositoryDetailDomainState.Complete ->
                RepositoryDetailPresentationState.Complete(repositoryMapper.toPresentation(domainModel.payload))
            is RepositoryDetailDomainState.Error ->
                RepositoryDetailPresentationState.Error(domainModel.payload)
        }
    }
}