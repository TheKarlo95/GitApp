package com.thekarlo95.data.datastore

import com.thekarlo95.data.model.OrderParamDataModel
import com.thekarlo95.data.model.RepositoryDataModel
import io.reactivex.Flowable

interface RepositoryDataStore {
    fun searchRepositories(
            search: String,
            page: Int = 1,
            order: OrderParamDataModel = OrderParamDataModel.STARS,
            perPage: Int = 10
    ): Flowable<List<RepositoryDataModel>>

    fun fetchRepositoryDetails(
            owner: String,
            repositoryName: String
    ): Flowable<RepositoryDataModel>
}