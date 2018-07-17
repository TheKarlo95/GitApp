package com.thekarlo95.search.ui.andorid.di.module

import com.thekarlo95.data.RepositoryDetailRepositoryImpl
import com.thekarlo95.data.SearchRepositoryImpl
import com.thekarlo95.data.UserRepositoryImpl
import com.thekarlo95.data.datastore.RepositoryDataStore
import com.thekarlo95.data.datastore.UserDataStore
import com.thekarlo95.data.mapper.RepositoryStateMapper
import com.thekarlo95.data.mapper.SearchStateMapper
import com.thekarlo95.data.mapper.UserStateMapper
import com.thekarlo95.domain.core.executor.PostExecutionThread
import com.thekarlo95.domain.core.executor.ThreadExecutor
import com.thekarlo95.domain.repodetail.repository.RepositoryDetailRepository
import com.thekarlo95.domain.reposearch.repository.SearchRepository
import com.thekarlo95.domain.user.repository.UserRepository
import com.thekarlo95.presentation.core.PresentationLogger
import com.thekarlo95.search.ui.andorid.core.logging.LoggerImpl
import com.thekarlo95.search.ui.andorid.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import hr.thekarlo95.api.github.GithubDataStoreImpl
import hr.thekarlo95.api.github.GithubService
import hr.thekarlo95.api.github.GithubServiceFactory
import hr.thekarlo95.api.github.mapper.RepositoryApiMapper
import hr.thekarlo95.api.github.mapper.UserApiMapper
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideGithubService(): GithubService {
        return GithubServiceFactory.create(true)
    }

    @Provides
    @PerApplication
    fun provideRepositoryDataStore(
            api: GithubService,
            repositoryMapper: RepositoryApiMapper,
            userMapper: UserApiMapper
    ): RepositoryDataStore {
        return GithubDataStoreImpl(api, repositoryMapper, userMapper)
    }

    @Provides
    @PerApplication
    fun provideUserDataStore(
            api: GithubService,
            repositoryMapper: RepositoryApiMapper,
            userMapper: UserApiMapper
    ): UserDataStore {
        return GithubDataStoreImpl(api, repositoryMapper, userMapper)
    }

    @Provides
    @PerApplication
    fun provideSearchRepository(
            dataStore: RepositoryDataStore,
            mapper: SearchStateMapper
    ): SearchRepository {
        return SearchRepositoryImpl(dataStore, mapper)
    }

    @Provides
    @PerApplication
    fun provideRepositoryDetailRepository(
            dataStore: RepositoryDataStore,
            mapper: RepositoryStateMapper
    ): RepositoryDetailRepository {
        return RepositoryDetailRepositoryImpl(dataStore, mapper)
    }

    @Provides
    @PerApplication
    fun provideUserRepository(
            dataStore: UserDataStore,
            mapper: UserStateMapper
    ): UserRepository {
        return UserRepositoryImpl(dataStore, mapper)
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(): ThreadExecutor {
        return object : ThreadExecutor {
            override val scheduler: Scheduler
                get() = Schedulers.io()
        }
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(): PostExecutionThread {
        return object : PostExecutionThread {
            override val scheduler: Scheduler
                get() = AndroidSchedulers.mainThread()
        }
    }

    @Provides
    @PerApplication
    internal fun providePresentationLoggerThread(): PresentationLogger {
        return LoggerImpl("presentation")
    }
}