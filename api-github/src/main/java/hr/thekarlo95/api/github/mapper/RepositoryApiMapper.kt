package hr.thekarlo95.api.github.mapper

import com.thekarlo95.data.model.RepositoryDataModel
import hr.thekarlo95.api.github.model.LicenseApiModel
import hr.thekarlo95.api.github.model.RepositoryApiModel
import javax.inject.Inject

class RepositoryApiMapper @Inject constructor(
        private val userMapper: UserApiMapper,
        private val licenseMapper: LicenseApiMapper
) : ApiMapper<RepositoryApiModel, RepositoryDataModel> {

    override fun toData(apiModel: RepositoryApiModel): RepositoryDataModel {
        return RepositoryDataModel(
                apiModel.id,
                apiModel.name,
                apiModel.fullName,
                apiModel.description ?: "",
                apiModel.forksCount,
                apiModel.starsCount,
                apiModel.language ?: "",
                apiModel.createdAt,
                apiModel.updatedAt,
                userMapper.toData(apiModel.owner),
                licenseMapper.toData(apiModel.license ?: LicenseApiModel("", ""))
        )
    }
}