package com.thekarlo95.core.search.data.mapper

import com.thekarlo95.core.search.data.model.LicenseDataModel
import com.thekarlo95.core.search.domain.model.LicenseDomainModel
import javax.inject.Inject

class LicenseDataMapper @Inject constructor() : DataMapper<LicenseDataModel, LicenseDomainModel> {
    override fun toDomain(dataModel: LicenseDataModel): LicenseDomainModel {
        return LicenseDomainModel(dataModel.key, dataModel.name)
    }

    override fun toData(domainModel: LicenseDomainModel): LicenseDataModel {
        return LicenseDataModel(domainModel.key, domainModel.name)
    }
}