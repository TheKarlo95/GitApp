package com.thekarlo95.data

import com.thekarlo95.data.datastore.RepositoryDataStore
import com.thekarlo95.data.mapper.RepositoryStateMapper
import com.thekarlo95.domain.repodetail.model.RepositoryDetailDomainState
import com.thekarlo95.domain.repodetail.repository.RepositoryDetailRepository
import io.reactivex.Flowable
import javax.inject.Inject

class RepositoryDetailRepositoryImpl @Inject constructor(
        private val dataStore: RepositoryDataStore,
        private val mapper: RepositoryStateMapper
) : RepositoryDetailRepository {

    override fun fetchRepositoryDetails(owner: String, repositoryName: String): Flowable<RepositoryDetailDomainState> {
        return dataStore.fetchRepositoryDetails(owner, repositoryName)
                .map { mapper.toDomain(it) }
                .startWith(RepositoryDetailDomainState.Loading)
                .onErrorReturn { RepositoryDetailDomainState.Error(it) }
    }
}