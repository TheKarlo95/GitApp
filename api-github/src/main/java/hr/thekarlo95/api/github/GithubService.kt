package hr.thekarlo95.api.github

import hr.thekarlo95.api.github.model.OrderParamApiModel
import hr.thekarlo95.api.github.model.RepositoryApiModel
import hr.thekarlo95.api.github.model.SearchResponse
import hr.thekarlo95.api.github.model.UserApiModel
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("search/repositories")
    fun searchRepositories(
            @Query("q") search: String,
            @Query("page") page: Int = 1,
            @Query("sort") order: String = OrderParamApiModel.STARS.param,
            @Query("per_page") perPage: Int = 10
    ) : Flowable<SearchResponse>

    @GET("/repos/{owner}/{repo}")
    fun fetchRepositoryDetails(
            @Path("owner") owner: String,
            @Path("repo") repositoryName: String
    ) : Flowable<RepositoryApiModel>

    @GET("/users/{username}")
    fun fetchUserDetails(
            @Path("username") username: String
    ) : Flowable<UserApiModel>
}