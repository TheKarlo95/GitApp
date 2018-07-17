package com.thekarlo95.presentation.repodetail.view

import com.thekarlo95.presentation.core.model.RepositoryPresentationModel
import com.thekarlo95.presentation.core.view.NoopView

object RepositoryDetailNoopView : NoopView(), RepositoryDetailView {
    override fun showRepository(repository: RepositoryPresentationModel) {
    }
}