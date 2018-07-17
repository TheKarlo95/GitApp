package hr.thekarlo95.api.github

import com.thekarlo95.data.datastore.RepositoryDataStore
import com.thekarlo95.data.datastore.UserDataStore
import com.thekarlo95.data.model.OrderParamDataModel
import com.thekarlo95.data.model.RepositoryDataModel
import com.thekarlo95.data.model.UserDataModel
import hr.thekarlo95.api.github.mapper.RepositoryApiMapper
import hr.thekarlo95.api.github.mapper.UserApiMapper
import io.reactivex.Flowable
import javax.inject.Inject

class GithubDataStoreImpl @Inject constructor(
        private val api: GithubService,
        private val repositoryMapper: RepositoryApiMapper,
        private val userMapper: UserApiMapper
) : RepositoryDataStore, UserDataStore {

    override fun searchRepositories(
            search: String,
            page: Int,
            order: OrderParamDataModel,
            perPage: Int
    ): Flowable<List<RepositoryDataModel>> {
        return api.searchRepositories(search, page, order.param, perPage)
                .map { repositoryMapper.toDataList(it.items) }
    }

    override fun fetchRepositoryDetails(
            owner: String,
            repositoryName: String
    ): Flowable<RepositoryDataModel> {
        return api.fetchRepositoryDetails(owner, repositoryName)
                .map { repositoryMapper.toData(it) }
    }

    override fun fetchUserDetails(username: String): Flowable<UserDataModel> {
        return api.fetchUserDetails(username)
                .map { userMapper.toData(it) }
    }
}