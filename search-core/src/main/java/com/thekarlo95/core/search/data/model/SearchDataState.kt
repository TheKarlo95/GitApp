package com.thekarlo95.core.search.data.model

sealed class SearchDataState {
    object Idle : SearchDataState()
    object Loading : SearchDataState()
    data class Complete(val payload: List<RepositoryDataModel>) : SearchDataState()
    data class Error(val payload: Throwable) : SearchDataState()
}
