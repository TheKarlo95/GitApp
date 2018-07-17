package com.thekarlo95.core.search.data.mapper

import com.thekarlo95.core.search.data.model.RepositoryDataModel
import com.thekarlo95.core.search.domain.model.RepositoryDomainModel
import javax.inject.Inject

class RepositoryDataMapper @Inject constructor(
        private val userMapper: UserDataMapper,
        private val licenseMapper: LicenseDataMapper
) : DataMapper<RepositoryDataModel, RepositoryDomainModel> {
    override fun toDomain(dataModel: RepositoryDataModel): RepositoryDomainModel {
        return RepositoryDomainModel(
                dataModel.id,
                dataModel.name,
                dataModel.fullName,
                dataModel.description,
                dataModel.forksCount,
                dataModel.starsCount,
                dataModel.language,
                dataModel.createdAt,
                dataModel.updatedAt,
                userMapper.toDomain(dataModel.owner),
                licenseMapper.toDomain(dataModel.license)
        )
    }

    override fun toData(domainModel: RepositoryDomainModel): RepositoryDataModel {
        return RepositoryDataModel(
                domainModel.id,
                domainModel.name,
                domainModel.fullName,
                domainModel.description,
                domainModel.forksCount,
                domainModel.starsCount,
                domainModel.language,
                domainModel.createdAt,
                domainModel.updatedAt,
                userMapper.toData(domainModel.owner),
                licenseMapper.toData(domainModel.license)
        )
    }
}