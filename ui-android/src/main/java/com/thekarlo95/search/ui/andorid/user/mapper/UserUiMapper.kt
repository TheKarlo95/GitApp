package com.thekarlo95.search.ui.andorid.user.mapper

import com.thekarlo95.presentation.core.model.UserPresentationModel
import com.thekarlo95.search.ui.andorid.core.mapper.UiMapper
import com.thekarlo95.search.ui.andorid.user.model.UserUiModel
import javax.inject.Inject

class UserUiMapper @Inject constructor() : UiMapper<UserPresentationModel, UserUiModel> {

    override fun toUi(presentationModel: UserPresentationModel): UserUiModel = with(presentationModel) {
        UserUiModel(id, name, type, avatarUrl, publicRepos, publicGists, followersCount)
    }
}