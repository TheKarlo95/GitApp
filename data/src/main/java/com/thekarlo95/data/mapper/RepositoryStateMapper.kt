package com.thekarlo95.data.mapper

import com.thekarlo95.data.model.LicenseDataModel
import com.thekarlo95.data.model.RepositoryDataModel
import com.thekarlo95.data.model.UserDataModel
import com.thekarlo95.domain.core.model.LicenseDomainModel
import com.thekarlo95.domain.core.model.RepositoryDomainModel
import com.thekarlo95.domain.core.model.UserDomainModel
import com.thekarlo95.domain.repodetail.model.RepositoryDetailDomainState
import javax.inject.Inject

class RepositoryStateMapper @Inject constructor() : DataMapper<RepositoryDataModel, RepositoryDetailDomainState> {

    override fun toDomain(dataModel: RepositoryDataModel): RepositoryDetailDomainState {
        return RepositoryDetailDomainState.Complete(dataModel.toDomain())
    }

    private fun RepositoryDataModel.toDomain(): RepositoryDomainModel {
        return RepositoryDomainModel(
                this.id,
                this.name,
                this.fullName,
                this.description,
                this.forksCount,
                this.starsCount,
                this.language,
                this.createdAt,
                this.updatedAt,
                this.owner.toDomain(),
                this.license.toDomain()
        )
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