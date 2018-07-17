package com.thekarlo95.search.ui.andorid.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.thekarlo95.presentation.core.model.UserPresentationModel
import com.thekarlo95.presentation.user.presenter.UserPresenter
import com.thekarlo95.presentation.user.view.UserView
import com.thekarlo95.search.ui.andorid.MainActivity
import com.thekarlo95.search.ui.andorid.R
import com.thekarlo95.search.ui.andorid.core.CoreFragment
import com.thekarlo95.search.ui.andorid.repositorydetail.RepositoryDetailFragment
import com.thekarlo95.search.ui.andorid.user.mapper.UserUiMapper
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_user.*
import java.text.DateFormat
import javax.inject.Inject

class UserFragment : CoreFragment<UserPresenter>(), UserView {

    companion object {
        val DATE_FORMAT: DateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
        const val USERNAME_EXTRA = "username"

        fun create(username: String): UserFragment {
            val fragment = UserFragment()
            val args = Bundle()
            args.putString(USERNAME_EXTRA, username)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var mapper: UserUiMapper
    @Inject
    override lateinit var presenter: UserPresenter

    private var username: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.view = this

        arguments?.let {
            username = it.getString(USERNAME_EXTRA)
            presenter.loadUser(username)
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun showUser(user: UserPresentationModel) = with(mapper.toUi(user)) {
        activity?.title = name

        Glide.with(this@UserFragment).load(avatarUrl).into(iv_user_image)
        tv_user_name.text = name
        tv_user_type.text = type
        tv_user_followers.text = (followersCount ?: 0).toString()
        tv_user_public_repos.text = (publicRepos ?: 0).toString()
        tv_user_public_gists.text = (publicGists ?: 0).toString()

        btn_show_browser.setOnClickListener {
            if (activity is MainActivity) {
                (activity as MainActivity).startWebBrowser("https://github.com/$name")
            }
        }
    }
}
