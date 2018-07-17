package com.thekarlo95.domain.user.interactor

import com.thekarlo95.domain.core.executor.PostExecutionThread
import com.thekarlo95.domain.core.executor.ThreadExecutor
import com.thekarlo95.domain.core.interactor.FlowableInteractor
import com.thekarlo95.domain.core.model.OrderParamDomainModel
import com.thekarlo95.domain.reposearch.model.SearchDomainState
import com.thekarlo95.domain.reposearch.repository.SearchRepository
import com.thekarlo95.domain.user.model.UserDomainState
import com.thekarlo95.domain.user.repository.UserRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetUserInteractor @Inject constructor(
        private val repository: UserRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : FlowableInteractor<UserDomainState, GetUserInteractor.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Flowable<UserDomainState> {
        return if (params == null) {
            Flowable.empty<UserDomainState>()
        } else {
            repository.fetchUserDetails(params.username)
        }
    }

    data class Params(
            val username: String
    )
}