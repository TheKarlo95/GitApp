package com.thekarlo95.search.ui.andorid.respositorysearch

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.jakewharton.rxbinding2.widget.textChangeEvents
import com.thekarlo95.presentation.core.model.OrderParamPresentationModel
import com.thekarlo95.presentation.core.model.RepositoryPresentationModel
import com.thekarlo95.presentation.reposearch.presenter.SearchPresenter
import com.thekarlo95.presentation.reposearch.view.SearchView
import com.thekarlo95.search.ui.andorid.R
import com.thekarlo95.search.ui.andorid.core.CoreActivity
import com.thekarlo95.search.ui.andorid.repositorydetail.RepositoryDetailActivity
import com.thekarlo95.search.ui.andorid.respositorysearch.mapper.RepositoryUiMapper
import com.thekarlo95.search.ui.andorid.user.UserActivity
import kotlinx.android.synthetic.main.activity_repository_search.*
import kotlinx.android.synthetic.main.content_repository_search.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositorySearchActivity : CoreActivity(), SearchView {

    @Inject
    lateinit var mapper: RepositoryUiMapper
    @Inject
    lateinit var presenter: SearchPresenter

    private val adapter = RepositoryAdapter(
            { owner, repository ->
                startActivity(RepositoryDetailActivity.createIntent(RepositorySearchActivity@ this, owner, repository))
            },
            {
                owner -> startActivity(UserActivity.createIntent(RepositorySearchActivity@ this, owner))
            }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_search)
        setSupportActionBar(toolbar)

        presenter.view = this

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_repositories.adapter = adapter
        rv_repositories.layoutManager = layoutManager
        rv_repositories.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                presenter.loadPage(page)
            }
        })
        rv_repositories.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        srl_repositories.setOnRefreshListener {
            if (et_search.text.isNotBlank()) {
                presenter.onSearchTextChange(et_search.text.toString())
            }
        }

        et_search.textChangeEvents()
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS)
                .map { it.text() }
                .subscribe { presenter.onSearchTextChange(it.toString()) }
                .addDisposable()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_sort, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_forks -> {
            presenter.sortBy(OrderParamPresentationModel.FORKS)
            true
        }
        R.id.menu_stars -> {
            presenter.sortBy(OrderParamPresentationModel.STARS)
            true
        }
        R.id.menu_updated -> {
            presenter.sortBy(OrderParamPresentationModel.UPDATED)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        srl_repositories.isRefreshing = true
    }

    override fun hideLoading() {
        srl_repositories.isRefreshing = false
    }

    override fun addToRepositoryList(repositories: List<RepositoryPresentationModel>) {
        val startIndex = adapter.itemCount
        adapter += mapper.toUiList(repositories)
        adapter.notifyItemRangeInserted(startIndex, repositories.size)
    }

    override fun clearList() = adapter.clear()
}
