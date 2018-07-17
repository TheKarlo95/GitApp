package com.thekarlo95.presentation.reposearch.model

import com.thekarlo95.presentation.core.model.RepositoryPresentationModel

sealed class SearchPresentationState {
    object Idle : SearchPresentationState()
    object Loading : SearchPresentationState()
    data class Complete(val payload: List<RepositoryPresentationModel>): SearchPresentationState()
    data class Error(val payload: Throwable): SearchPresentationState()
}
