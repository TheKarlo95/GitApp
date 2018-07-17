package hr.thekarlo95.api.github.mapper

import com.thekarlo95.data.model.LicenseDataModel
import hr.thekarlo95.api.github.model.LicenseApiModel
import javax.inject.Inject

class LicenseApiMapper @Inject constructor() : ApiMapper<LicenseApiModel, LicenseDataModel> {

    override fun toData(apiModel: LicenseApiModel): LicenseDataModel {
        return LicenseDataModel(apiModel.key, apiModel.name)
    }
}