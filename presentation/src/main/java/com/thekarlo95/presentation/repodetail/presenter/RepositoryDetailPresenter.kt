package com.thekarlo95.presentation.repodetail.presenter

import com.sun.corba.se.spi.activation.RepositoryOperations
import com.thekarlo95.presentation.core.model.OrderParamPresentationModel
import com.thekarlo95.presentation.core.presenter.Presenter
import com.thekarlo95.presentation.repodetail.view.RepositoryDetailView
import com.thekarlo95.presentation.reposearch.view.SearchView

interface RepositoryDetailPresenter : Presenter {

    var view: RepositoryDetailView

    fun loadRepository(ownerName: String, repositoryName: String)
}