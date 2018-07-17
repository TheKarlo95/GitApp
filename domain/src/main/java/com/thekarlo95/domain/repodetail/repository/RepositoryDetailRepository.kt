package com.thekarlo95.domain.repodetail.repository

import com.thekarlo95.domain.repodetail.model.RepositoryDetailDomainState
import io.reactivex.Flowable

interface RepositoryDetailRepository {

    fun fetchRepositoryDetails(
            owner: String,
            repositoryName: String
    ): Flowable<RepositoryDetailDomainState>
}