package com.thekarlo95.core.repodetail.domain.repository

import com.thekarlo95.core.repodetail.domain.model.OrderParamDomainModel
import com.thekarlo95.core.repodetail.domain.model.RepositoryDomainState
import io.reactivex.Flowable

interface RepositoryDetailRepository {

    fun fetchRepositoryDetails(
            owner: String,
            repositoryName: String
    ): Flowable<RepositoryDomainState>
}