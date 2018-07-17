package com.thekarlo95.search.ui.andorid.respositorysearch.mapper

import com.thekarlo95.presentation.core.model.RepositoryPresentationModel
import com.thekarlo95.search.ui.andorid.core.mapper.UiMapper
import com.thekarlo95.search.ui.andorid.respositorysearch.model.RepositoryUiModel
import javax.inject.Inject

class RepositoryUiMapper @Inject constructor() : UiMapper<RepositoryPresentationModel, RepositoryUiModel> {
    override fun toUi(presentationModel: RepositoryPresentationModel): RepositoryUiModel {
        return RepositoryUiModel(
                presentationModel.id,
                presentationModel.name,
                presentationModel.userAvatarUrl,
                presentationModel.fullName,
                presentationModel.description,
                presentationModel.forksCount,
                presentationModel.starsCount,
                presentationModel.language,
                presentationModel.createdAt,
                presentationModel.updatedAt,
                presentationModel.owner.name,
                presentationModel.license
        )
    }
}