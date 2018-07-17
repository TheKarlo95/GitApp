package com.thekarlo95.domain.repodetail.interactor

import com.thekarlo95.domain.core.executor.PostExecutionThread
import com.thekarlo95.domain.core.executor.ThreadExecutor
import com.thekarlo95.domain.core.interactor.FlowableInteractor
import com.thekarlo95.domain.repodetail.model.RepositoryDetailDomainState
import com.thekarlo95.domain.repodetail.repository.RepositoryDetailRepository
import com.thekarlo95.domain.reposearch.model.SearchDomainState
import io.reactivex.Flowable
import javax.inject.Inject

class GetRepositoryDetailInteractor @Inject constructor(
        private val repository: RepositoryDetailRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : FlowableInteractor<RepositoryDetailDomainState, GetRepositoryDetailInteractor.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Flowable<RepositoryDetailDomainState> {
        return if (params == null) {
            Flowable.empty<RepositoryDetailDomainState>()
        } else {
            repository.fetchRepositoryDetails(params.owner, params.repositoryName)
        }
    }

    data class Params(
            val owner: String,
            val repositoryName: String
    )
}