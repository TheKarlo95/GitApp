package com.thekarlo95.presentation.reposearch.view

import com.thekarlo95.presentation.core.model.RepositoryPresentationModel
import com.thekarlo95.presentation.core.view.NoopView

object SearchNoopView : NoopView(), SearchView {

    override fun addToRepositoryList(repositories: List<RepositoryPresentationModel>) {}

    override fun clearList() {}
}