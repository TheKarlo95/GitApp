package com.thekarlo95.core.search.data.mapper

import com.thekarlo95.core.search.data.model.UserDataModel
import com.thekarlo95.core.search.domain.model.UserDomainModel
import javax.inject.Inject

class UserDataMapper @Inject constructor() : DataMapper<UserDataModel, UserDomainModel> {
    override fun toDomain(dataModel: UserDataModel): UserDomainModel {
        return UserDomainModel(
                dataModel.id,
                dataModel.name,
                dataModel.type,
                dataModel.avatarUrl,
                dataModel.publicRepos,
                dataModel.publicGists,
                dataModel.followersCount
        )
    }

    override fun toData(domainModel: UserDomainModel): UserDataModel {
        return UserDataModel(
                domainModel.id,
                domainModel.name,
                domainModel.type,
                domainModel.avatarUrl,
                domainModel.publicRepos,
                domainModel.publicGists,
                domainModel.followersCount
        )
    }
}