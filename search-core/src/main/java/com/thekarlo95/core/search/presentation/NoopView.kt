package com.thekarlo95.core.search.presentation

import com.thekarlo95.core.search.presentation.model.RepositoryPresentationModel

object NoopView : SearchView {

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun addToRepositoryList(repositories: List<RepositoryPresentationModel>) {}

    override fun showError(errorMessage: String) {}

    override fun clearList() {}
}