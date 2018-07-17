package com.thekarlo95.domain.user.repository

import com.thekarlo95.domain.core.model.OrderParamDomainModel
import com.thekarlo95.domain.reposearch.model.SearchDomainState
import com.thekarlo95.domain.user.model.UserDomainState
import io.reactivex.Flowable

interface UserRepository {

    fun fetchUserDetails(username: String): Flowable<UserDomainState>
}