package com.thekarlo95.presentation.repodetail.view

import com.thekarlo95.presentation.core.model.RepositoryPresentationModel
import com.thekarlo95.presentation.core.view.View

interface RepositoryDetailView : View {

    fun showRepository(repository: RepositoryPresentationModel)
}