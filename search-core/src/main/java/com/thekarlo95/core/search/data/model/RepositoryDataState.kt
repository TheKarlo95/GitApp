package com.thekarlo95.core.search.data.model

sealed class RepositoryDataState {
    object Idle : RepositoryDataState()
    object Loading : RepositoryDataState()
    data class Complete(val payload: RepositoryDataModel) : RepositoryDataState()
    data class Error(val payload: Throwable) : RepositoryDataState()
}