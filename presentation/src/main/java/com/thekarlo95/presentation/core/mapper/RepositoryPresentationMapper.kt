package com.thekarlo95.presentation.core.mapper

import com.thekarlo95.domain.core.model.RepositoryDomainModel
import com.thekarlo95.domain.core.model.UserDomainModel
import com.thekarlo95.presentation.core.model.RepositoryPresentationModel
import com.thekarlo95.presentation.core.model.UserPresentationModel
import javax.inject.Inject

class RepositoryPresentationMapper @Inject constructor()
    : PresentationMapper<RepositoryDomainModel, RepositoryPresentationModel> {
    override fun toPresentation(domainModel: RepositoryDomainModel): RepositoryPresentationModel {
        return RepositoryPresentationModel(
                domainModel.id,
                domainModel.name,
                domainModel.owner.avatarUrl,
                domainModel.fullName,
                domainModel.description,
                domainModel.forksCount,
                domainModel.starsCount,
                domainModel.language,
                domainModel.createdAt,
                domainModel.updatedAt,
                domainModel.owner.toPresentation(),
                domainModel.license.name
        )
    }

    private fun UserDomainModel.toPresentation(): UserPresentationModel = with(this) {
        UserPresentationModel(id, name, type, avatarUrl, publicRepos, publicGists, followersCount)
    }
}

