package com.thekarlo95.search.ui.andorid.respositorysearch

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.jakewharton.rxbinding2.widget.textChangeEvents
import com.thekarlo95.presentation.core.model.OrderParamPresentationModel
import com.thekarlo95.presentation.core.model.RepositoryPresentationModel
import com.thekarlo95.presentation.reposearch.presenter.SearchPresenter
import com.thekarlo95.presentation.reposearch.view.SearchView
import com.thekarlo95.search.ui.andorid.MainActivity
import com.thekarlo95.search.ui.andorid.R
import com.thekarlo95.search.ui.andorid.core.CoreFragment
import com.thekarlo95.search.ui.andorid.repositorydetail.RepositoryDetailFragment
import com.thekarlo95.search.ui.andorid.respositorysearch.mapper.RepositoryUiMapper
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_repository_search.*
import kotlinx.android.synthetic.main.content_repository_search.*
import java.text.DateFormat
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class RepositorySearchFragment : CoreFragment<SearchPresenter>(), SearchView, AdapterView.OnItemSelectedListener {

    companion object {
        fun create(): RepositorySearchFragment {
            return RepositorySearchFragment()
        }
    }

    @Inject
    lateinit var mapper: RepositoryUiMapper
    @Inject
    override lateinit var presenter: SearchPresenter

    private val adapter = RepositoryAdapter(
            { owner, repository ->
                if (activity is MainActivity) {
                    (activity as MainActivity).startRepositoryDetailFragment(owner, repository)
                }
            },
            { owner ->
                if (activity is MainActivity) {
                    (activity as MainActivity).startUserFragment(owner)
                }
            }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.view = this
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_repository_search, container, false)
        activity?.title = "Repository search"
        configureRecyclerView(layout)
        configureSearchEditText(layout)
        configureOrderSpinner(layout)
        return layout
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.getItemAtPosition(position) as String) {
            "Stars" -> presenter.sortBy(OrderParamPresentationModel.STARS)
            "Forks" -> presenter.sortBy(OrderParamPresentationModel.FORKS)
            "Updated" -> presenter.sortBy(OrderParamPresentationModel.UPDATED)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun showLoading() {
        srl_repositories?.isRefreshing = true
    }

    override fun hideLoading() {
        srl_repositories?.isRefreshing = false
    }

    override fun addToRepositoryList(repositories: List<RepositoryPresentationModel>) {
        val startIndex = adapter.itemCount
        adapter += mapper.toUiList(repositories)
        adapter.notifyItemRangeInserted(startIndex, repositories.size)
    }

    override fun clearList() = adapter.clear()

    private fun configureRecyclerView(layout: View) {
        val rvRepositories = layout.findViewById<RecyclerView>(R.id.rv_repositories)
        val srlRepositories = layout.findViewById<SwipeRefreshLayout>(R.id.srl_repositories)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvRepositories.adapter = adapter
        rvRepositories.layoutManager = layoutManager
        rvRepositories.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                presenter.loadPage(page)
            }
        })
        rvRepositories.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        srlRepositories.setOnRefreshListener {
            if (et_search.text.isNotBlank()) {
                presenter.onSearchTextChange(et_search.text.toString())
            }
        }
    }

    private fun configureSearchEditText(layout: View) {
        val etSearch = layout.findViewById<EditText>(R.id.et_search)

        etSearch.textChangeEvents()
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS)
                .map { it.text() }
                .subscribe { presenter.onSearchTextChange(it.toString()) }
                .addDisposable()
    }

    private fun configureOrderSpinner(layout: View) {
        val spOrder = layout.findViewById<Spinner>(R.id.sp_order)

        val adapter = ArrayAdapter.createFromResource(context, R.array.order_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spOrder.adapter = adapter
        spOrder.onItemSelectedListener = this
    }
}
