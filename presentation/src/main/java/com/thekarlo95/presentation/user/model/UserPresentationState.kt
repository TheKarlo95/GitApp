package com.thekarlo95.presentation.user.model

import com.thekarlo95.presentation.core.model.UserPresentationModel

sealed class UserPresentationState {
    object Idle : UserPresentationState()
    object Loading : UserPresentationState()
    data class Complete(val payload: UserPresentationModel): UserPresentationState()
    data class Error(val payload: Throwable): UserPresentationState()
}
