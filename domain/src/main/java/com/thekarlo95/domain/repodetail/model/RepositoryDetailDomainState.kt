package com.thekarlo95.domain.repodetail.model

import com.thekarlo95.domain.core.model.RepositoryDomainModel

sealed class RepositoryDetailDomainState {
    object Idle : RepositoryDetailDomainState()
    object Loading : RepositoryDetailDomainState()
    data class Complete(val payload: RepositoryDomainModel) : RepositoryDetailDomainState()
    data class Error(val payload: Throwable) : RepositoryDetailDomainState()
}
