package com.thekarlo95.presentation.repodetail.model

import com.thekarlo95.presentation.core.model.RepositoryPresentationModel

sealed class RepositoryDetailPresentationState {
    object Idle : RepositoryDetailPresentationState()
    object Loading : RepositoryDetailPresentationState()
    data class Complete(val payload: RepositoryPresentationModel): RepositoryDetailPresentationState()
    data class Error(val payload: Throwable): RepositoryDetailPresentationState()
}
