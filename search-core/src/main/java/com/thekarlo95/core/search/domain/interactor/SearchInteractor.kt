package com.thekarlo95.core.search.domain.interactor

import com.thekarlo95.core.search.domain.executor.PostExecutionThread
import com.thekarlo95.core.search.domain.executor.ThreadExecutor
import com.thekarlo95.core.search.domain.model.OrderParamDomainModel
import com.thekarlo95.core.search.domain.model.SearchDomainState
import com.thekarlo95.core.search.domain.repository.SearchRepository
import com.thekarlo95.core.search.presentation.mapper.SearchStateMapper
import com.thekarlo95.core.search.presentation.model.OrderParamPresentationModel
import com.thekarlo95.core.search.presentation.model.SearchPresentationState
import io.reactivex.Flowable
import javax.inject.Inject

class SearchInteractor @Inject constructor(
        private val repository: SearchRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : FlowableInteractor<SearchDomainState, SearchInteractor.SearchParams>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: SearchParams?): Flowable<SearchDomainState> {
        var observable = Flowable.empty<SearchDomainState>()
        params?.let {
            observable = repository.searchRepositories(
                    it.search,
                    it.page,
                    it.order.toDomain(),
                    it.perPage
            )
        }
        return observable
    }

    private fun OrderParamPresentationModel.toDomain(): OrderParamDomainModel {
        return when (this) {
            OrderParamPresentationModel.FORKS -> OrderParamDomainModel.FORKS
            OrderParamPresentationModel.STARS -> OrderParamDomainModel.STARS
            OrderParamPresentationModel.UPDATED -> OrderParamDomainModel.UPDATED
        }
    }

    data class SearchParams(
            val search: String,
            val page: Int = 1,
            val order: OrderParamPresentationModel = OrderParamPresentationModel.STARS,
            val perPage: Int = 10
    )
}