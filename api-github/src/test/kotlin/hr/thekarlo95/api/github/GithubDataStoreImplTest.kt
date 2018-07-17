package hr.thekarlo95.api.github

import hr.thekarlo95.api.github.model.LicenseApiModel
import hr.thekarlo95.api.github.model.RepositoryApiModel
import hr.thekarlo95.api.github.model.UserApiModel
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response
import java.util.*

class GithubDataStoreImplTest {

    @Test
    fun test_create() {
        val datasource = mock(GithubService::class.java).toDatasource()
        checkNotNull(datasource)
    }

    @Test
    fun test_searchRepositories_complete_empty() {
        val mock = mock(GithubService::class.java)
        `when`(mock.searchRepositories("query"))
                .thenReturn(Flowable.fromCallable { Response.success(emptyList<RepositoryApiModel>()) })
        val datasource = mock.toDatasource()

        val subscriber = TestSubscriber<SearchApiState>()
        datasource.searchRepositories("query").subscribe(subscriber)

        subscriber.assertNoErrors()
        subscriber.assertValues(
                SearchApiState.Loading,
                SearchApiState.Complete(emptyList())
        )
    }


    @Test
    fun test_searchRepositories_complete_values() {
        val user = UserApiModel(1L, "karlo", "user", "http://someurl.com", 3, 3, 3)
        val license = LicenseApiModel("MIT", "MIT")
        val data = listOf(
                RepositoryApiModel(1L, "repo-1", "${user.name}/repo-1", "Description of repo 1", 3, 5, "java", Date(), Date(), user, license),
                RepositoryApiModel(2L, "repo-2", "${user.name}/repo-2", "Description of repo 2", 4, 7, "kotlin", Date(), Date(), user, license),
                RepositoryApiModel(3L, "repo-3", "${user.name}/repo-3", "Description of repo 3", 3, 2, "clojure", Date(), Date(), user, license),
                RepositoryApiModel(4L, "repo-4", "${user.name}/repo-4", "Description of repo 4", 4, 7, "javascript", Date(), Date(), user, license),
                RepositoryApiModel(5L, "repo-5", "${user.name}/repo-5", "Description of repo 5", 3, 9, "haskell", Date(), Date(), user, license)
        )
        val mock = mock(GithubService::class.java)
        `when`(mock.searchRepositories("query"))
                .thenReturn(Flowable.fromCallable { Response.success(data) })
        val datasource = mock.toDatasource()

        val subscriber = TestSubscriber<SearchApiState>()
        datasource.searchRepositories("query").subscribe(subscriber)

        subscriber.assertNoErrors()
        subscriber.assertValues(
                SearchApiState.Loading,
                SearchApiState.Complete(data)
        )
    }

    @Test
    fun test_searchRepositories_error() {
        val mock = mock(GithubService::class.java)
        `when`(mock.searchRepositories(""))
                .thenReturn(Flowable.fromCallable {
                    Response.error<List<RepositoryApiModel>>(422, ResponseBody.create(
                            MediaType.parse("application/json"),
                            "{\n" +
                                    "  \"message\": \"Validation Failed\",\n" +
                                    "  \"errors\": [\n" +
                                    "    {\n" +
                                    "      \"resource\": \"Search\",\n" +
                                    "      \"field\": \"q\",\n" +
                                    "      \"code\": \"missing\"\n" +
                                    "    }\n" +
                                    "  ],\n" +
                                    "  \"documentation_url\": \"https://developer.github.com/v3/search\"\n" +
                                    "}"
                    ))
                })
        val datasource = mock.toDatasource()
    }

    private fun GithubService.toDatasource() = GithubDataStoreImpl(this)
}