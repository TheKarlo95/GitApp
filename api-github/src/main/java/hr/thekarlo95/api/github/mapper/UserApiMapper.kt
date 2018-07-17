package hr.thekarlo95.api.github.mapper

import com.thekarlo95.data.model.UserDataModel
import hr.thekarlo95.api.github.model.UserApiModel
import javax.inject.Inject

class UserApiMapper @Inject constructor() : ApiMapper<UserApiModel, UserDataModel> {

    override fun toData(apiModel: UserApiModel): UserDataModel {
        return UserDataModel(
                apiModel.id,
                apiModel.name,
                apiModel.type,
                apiModel.avatarUrl,
                apiModel.publicRepos,
                apiModel.publicGists,
                apiModel.followersCount
        )
    }
}