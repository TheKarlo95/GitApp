package com.thekarlo95.presentation.core.mapper

import com.thekarlo95.domain.core.model.UserDomainModel
import com.thekarlo95.presentation.core.model.UserPresentationModel
import javax.inject.Inject

class UserPresentationMapper @Inject constructor() : PresentationMapper<UserDomainModel, UserPresentationModel> {

    override fun toPresentation(domainModel: UserDomainModel): UserPresentationModel  = with(domainModel ) {
        UserPresentationModel(id, name, type, avatarUrl, publicRepos, publicGists, followersCount)
    }
}

