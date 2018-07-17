package com.thekarlo95.core.search.presentation.model

sealed class SearchPresentationState {
    object Idle : SearchPresentationState()
    object Loading : SearchPresentationState()
    data class Complete(val payload: List<RepositoryPresentationModel>): SearchPresentationState()
    data class Error(val payload: Throwable): SearchPresentationState()
}
