package com.thekarlo95.data.mapper

import com.thekarlo95.data.model.UserDataModel
import com.thekarlo95.domain.core.model.UserDomainModel
import com.thekarlo95.domain.user.model.UserDomainState
import javax.inject.Inject

class UserStateMapper @Inject constructor() : DataMapper<UserDataModel, UserDomainState> {

    override fun toDomain(dataModel: UserDataModel): UserDomainState {
        return UserDomainState.Complete(dataModel.toDomain())
    }

    private fun UserDataModel.toDomain(): UserDomainModel = with(this) {
        UserDomainModel(id, name, type, avatarUrl, publicRepos, publicGists, followersCount)
    }
}