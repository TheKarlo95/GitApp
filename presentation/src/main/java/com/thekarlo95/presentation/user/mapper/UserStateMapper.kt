package com.thekarlo95.presentation.user.mapper

import com.thekarlo95.domain.repodetail.model.RepositoryDetailDomainState
import com.thekarlo95.domain.user.model.UserDomainState
import com.thekarlo95.presentation.core.mapper.PresentationMapper
import com.thekarlo95.presentation.core.mapper.RepositoryPresentationMapper
import com.thekarlo95.presentation.core.mapper.UserPresentationMapper
import com.thekarlo95.presentation.repodetail.model.RepositoryDetailPresentationState
import com.thekarlo95.presentation.user.model.UserPresentationState
import javax.inject.Inject

class UserStateMapper @Inject constructor(
        private val userMapper: UserPresentationMapper
) : PresentationMapper<UserDomainState, UserPresentationState> {

    override fun toPresentation(domainModel: UserDomainState): UserPresentationState = when (domainModel) {
        is UserDomainState.Idle -> UserPresentationState.Idle
        is UserDomainState.Loading -> UserPresentationState.Loading
        is UserDomainState.Complete -> UserPresentationState.Complete(userMapper.toPresentation(domainModel.payload))
        is UserDomainState.Error -> UserPresentationState.Error(domainModel.payload)
    }
}