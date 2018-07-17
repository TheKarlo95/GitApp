package com.thekarlo95.domain.user.model

import com.thekarlo95.domain.core.model.UserDomainModel

sealed class UserDomainState {
    object Idle : UserDomainState()
    object Loading : UserDomainState()
    data class Complete(val payload: UserDomainModel) : UserDomainState()
    data class Error(val payload: Throwable) : UserDomainState()
}
