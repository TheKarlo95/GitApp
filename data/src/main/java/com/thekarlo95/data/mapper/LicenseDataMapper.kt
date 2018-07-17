package com.thekarlo95.data.mapper

import com.thekarlo95.data.model.LicenseDataModel
import com.thekarlo95.domain.core.model.LicenseDomainModel
import javax.inject.Inject

class LicenseDataMapper @Inject constructor() : DataMapper<LicenseDataModel, LicenseDomainModel> {
    override fun toDomain(dataModel: LicenseDataModel): LicenseDomainModel {
        return LicenseDomainModel(dataModel.key, dataModel.name)
    }
}