package com.thekarlo95.data

import com.thekarlo95.data.datastore.RepositoryDataStore
import com.thekarlo95.data.datastore.UserDataStore
import com.thekarlo95.data.mapper.SearchStateMapper
import com.thekarlo95.data.mapper.UserStateMapper
import com.thekarlo95.data.model.OrderParamDataModel
import com.thekarlo95.domain.core.model.OrderParamDomainModel
import com.thekarlo95.domain.reposearch.model.SearchDomainState
import com.thekarlo95.domain.user.model.UserDomainState
import com.thekarlo95.domain.user.repository.UserRepository
import io.reactivex.Flowable
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
        private val dataStore: UserDataStore,
        private val mapper: UserStateMapper
) : UserRepository {

    override fun fetchUserDetails(username: String): Flowable<UserDomainState> {
        return dataStore.fetchUserDetails(username)
                .map { mapper.toDomain(it) }
                .startWith(UserDomainState.Loading)
                .onErrorReturn { UserDomainState.Error(it) }
    }
}