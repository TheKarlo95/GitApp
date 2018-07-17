package com.thekarlo95.core.repodetail.domain.interactor

import com.thekarlo95.core.repodetail.domain.executor.PostExecutionThread
import com.thekarlo95.core.repodetail.domain.executor.ThreadExecutor
import com.thekarlo95.core.repodetail.domain.model.OrderParamDomainModel
import com.thekarlo95.core.repodetail.domain.model.RepositoryDomainState
import com.thekarlo95.core.repodetail.domain.repository.RepositoryDetailRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetRepositoryDetailInteractor @Inject constructor(
        private val repository: RepositoryDetailRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : FlowableInteractor<RepositoryDomainState, GetRepositoryDetailInteractor.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Flowable<RepositoryDomainState> {
        var observable = Flowable.empty<RepositoryDomainState>()
        params?.let {
            observable = repository.fetchRepositoryDetails(
                    params.owner,
                    params.repositoryName
            )
        }
        return observable
    }

    data class Params(
            val owner: String,
            val repositoryName: String
    )
}