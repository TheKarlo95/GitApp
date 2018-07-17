package com.thekarlo95.core.repodetail.domain.model

sealed class RepositoryDomainState {
    object Idle : RepositoryDomainState()
    object Loading : RepositoryDomainState()
    data class Complete(val payload: RepositoryDomainModel) : RepositoryDomainState()
    data class Error(val payload: Throwable) : RepositoryDomainState()
}
