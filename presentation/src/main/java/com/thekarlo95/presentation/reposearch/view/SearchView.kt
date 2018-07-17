package com.thekarlo95.presentation.reposearch.view

import com.thekarlo95.presentation.core.view.View
import com.thekarlo95.presentation.core.model.RepositoryPresentationModel

interface SearchView : View {

    fun addToRepositoryList(repositories: List<RepositoryPresentationModel>)

    fun clearList()
}