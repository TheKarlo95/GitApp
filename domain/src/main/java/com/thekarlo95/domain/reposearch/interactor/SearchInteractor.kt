package com.thekarlo95.domain.reposearch.interactor

import com.thekarlo95.domain.core.executor.PostExecutionThread
import com.thekarlo95.domain.core.executor.ThreadExecutor
import com.thekarlo95.domain.core.interactor.FlowableInteractor
import com.thekarlo95.domain.core.model.OrderParamDomainModel
import com.thekarlo95.domain.reposearch.model.SearchDomainState
import com.thekarlo95.domain.reposearch.repository.SearchRepository
import com.thekarlo95.domain.user.model.UserDomainState
import io.reactivex.Flowable
import javax.inject.Inject

class SearchInteractor @Inject constructor(
        private val repository: SearchRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : FlowableInteractor<SearchDomainState, SearchInteractor.SearchParams>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: SearchParams?): Flowable<SearchDomainState> {
        return if (params == null) {
            Flowable.empty<SearchDomainState>()
        } else {
            with(params) { repository.searchRepositories(search, page, order, perPage) }
        }
    }

    data class SearchParams(
            val search: String,
            val page: Int = 1,
            val order: OrderParamDomainModel = OrderParamDomainModel.STARS,
            val perPage: Int = 10
    )
}