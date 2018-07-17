package com.thekarlo95.core.search.presentation

import com.thekarlo95.core.search.presentation.model.RepositoryPresentationModel

interface SearchView {

    fun showLoading()

    fun hideLoading()

    fun addToRepositoryList(repositories: List<RepositoryPresentationModel>)

    fun clearList()

    fun showError(errorMessage: String)
}