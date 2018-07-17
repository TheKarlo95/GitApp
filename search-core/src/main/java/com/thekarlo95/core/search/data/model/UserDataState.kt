package com.thekarlo95.core.search.data.model

sealed class UserDataState {
    object Idle : UserDataState()
    object Loading : UserDataState()
    data class Complete(val payload: UserDataModel) : UserDataState()
    data class Error(val payload: Throwable) : UserDataState()
}