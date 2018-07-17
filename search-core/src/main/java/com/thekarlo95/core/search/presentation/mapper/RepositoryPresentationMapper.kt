package com.thekarlo95.core.search.presentation.mapper

import com.thekarlo95.core.search.domain.model.RepositoryDomainModel
import com.thekarlo95.core.search.presentation.model.RepositoryPresentationModel
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
                domainModel.owner.name,
                domainModel.license.name
        )
    }
}