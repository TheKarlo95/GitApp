package com.thekarlo95.core.search.domain.model

sealed class SearchDomainState {
    object Idle : SearchDomainState()
    object Loading : SearchDomainState()
    data class Complete(val payload: List<RepositoryDomainModel>) : SearchDomainState()
    data class Error(val payload: Throwable) : SearchDomainState()
}
