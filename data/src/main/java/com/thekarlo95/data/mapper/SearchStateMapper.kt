package com.thekarlo95.data.mapper

import com.thekarlo95.data.model.LicenseDataModel
import com.thekarlo95.data.model.RepositoryDataModel
import com.thekarlo95.data.model.UserDataModel
import com.thekarlo95.domain.core.model.LicenseDomainModel
import com.thekarlo95.domain.core.model.RepositoryDomainModel
import com.thekarlo95.domain.core.model.UserDomainModel
import com.thekarlo95.domain.reposearch.model.SearchDomainState
import javax.inject.Inject

class SearchStateMapper @Inject constructor() : DataMapper<List<RepositoryDataModel>, SearchDomainState> {

    override fun toDomain(dataModel: List<RepositoryDataModel>): SearchDomainState {
        return SearchDomainState.Complete(dataModel.toDomain())
    }

    private fun List<RepositoryDataModel>.toDomain(): List<RepositoryDomainModel> {
        return this.map {
            RepositoryDomainModel(
                    it.id,
                    it.name,
                    it.fullName,
                    it.description,
                    it.forksCount,
                    it.starsCount,
                    it.language,
                    it.createdAt,
                    it.updatedAt,
                    it.owner.toDomain(),
                    it.license.toDomain()
            )
        }
    }

    private fun UserDataModel.toDomain(): UserDomainModel {
        return UserDomainModel(
                this.id,
                this.name,
                this.type,
                this.avatarUrl,
                this.publicRepos,
                this.publicGists,
                this.followersCount
        )
    }

    private fun LicenseDataModel.toDomain(): LicenseDomainModel {
        return LicenseDomainModel(
                this.key,
                this.name
        )
    }
}