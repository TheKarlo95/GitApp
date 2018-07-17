package com.thekarlo95.search.ui.andorid.repositorydetail

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.thekarlo95.presentation.core.model.RepositoryPresentationModel
import com.thekarlo95.presentation.repodetail.presenter.RepositoryDetailPresenter
import com.thekarlo95.presentation.repodetail.view.RepositoryDetailView
import com.thekarlo95.search.ui.andorid.MainActivity
import com.thekarlo95.search.ui.andorid.R
import com.thekarlo95.search.ui.andorid.core.CoreFragment
import com.thekarlo95.search.ui.andorid.repositorydetail.mapper.RepositoryUiMapper
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_repository_detail.*
import java.text.DateFormat
import javax.inject.Inject

class RepositoryDetailFragment : CoreFragment<RepositoryDetailPresenter>(), RepositoryDetailView {

    companion object {
        val DATE_FORMAT: DateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
        const val OWNER_EXTRA = "owner_name"
        const val REPOSITORY_EXTRA = "repository_name"

        fun create(ownerName: String, repositoryName: String): RepositoryDetailFragment {
            val fragment = RepositoryDetailFragment()
            val args = Bundle()
            args.putString(OWNER_EXTRA, ownerName)
            args.putString(REPOSITORY_EXTRA, repositoryName)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var mapper: RepositoryUiMapper
    @Inject
    override lateinit var presenter: RepositoryDetailPresenter

    private var owner: String = ""
    private var repo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.view = this

        arguments?.let {
            owner = it.getString(OWNER_EXTRA)
            repo = it.getString(REPOSITORY_EXTRA)

            presenter.loadRepository(owner, repo)
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repository_detail, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun showRepository(repository: RepositoryPresentationModel) = with(mapper.toUi(repository)) {
        activity?.title = fullName

        Glide.with(this@RepositoryDetailFragment).load(userAvatarUrl).into(iv_repository_image)
        tv_repository.text = fullName
        tv_created_at.text = DATE_FORMAT.format(createdAt)
        tv_updated_at.text = DATE_FORMAT.format(updatedAt)
        tv_description.text = description
        tv_user_name.text = owner.name
        but_user_info.text = "More about ${owner.name}"
        but_user_info.setOnClickListener {
            if (activity is MainActivity) {
                (activity as MainActivity).startUserFragment(owner.name)
            }
        }
        btn_show_browser.setOnClickListener {
            if (activity is MainActivity) {
                (activity as MainActivity).startWebBrowser("https://github.com/$owner/$repo")
            }
        }
    }
}
