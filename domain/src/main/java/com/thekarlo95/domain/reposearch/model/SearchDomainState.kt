package com.thekarlo95.domain.reposearch.model

import com.thekarlo95.domain.core.model.RepositoryDomainModel

sealed class SearchDomainState {
    object Idle : SearchDomainState()
    object Loading : SearchDomainState()
    data class Complete(val payload: List<RepositoryDomainModel>) : SearchDomainState()
    data class Error(val payload: Throwable) : SearchDomainState()
}
