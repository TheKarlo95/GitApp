package com.thekarlo95.search.ui.andorid.user

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.thekarlo95.presentation.core.model.UserPresentationModel
import com.thekarlo95.presentation.user.presenter.UserPresenter
import com.thekarlo95.presentation.user.view.UserView
import com.thekarlo95.search.ui.andorid.R
import com.thekarlo95.search.ui.andorid.core.CoreActivity
import com.thekarlo95.search.ui.andorid.user.mapper.UserUiMapper
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.content_user.*
import java.text.DateFormat
import javax.inject.Inject

class UserActivity : CoreActivity(), UserView {

    companion object {
        val DATE_FORMAT: DateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
        const val USERNAME_EXTRA = "username"

        fun createIntent(context: Context, username: String): Intent {
            return Intent(context, UserActivity::class.java)
                    .putExtra(USERNAME_EXTRA, username)
        }
    }

    @Inject
    lateinit var mapper: UserUiMapper
    @Inject
    lateinit var presenter: UserPresenter

    private var username: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setSupportActionBar(toolbar)

        presenter.view = this

        username = intent.getStringExtra(USERNAME_EXTRA)
        presenter.loadUser(username)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_web, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_web -> {
            startWebBrowser()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun showUser(user: UserPresentationModel) = with(mapper.toUi(user)) {
        toolbar.title = name
        Glide.with(this@UserActivity).load(avatarUrl).into(iv_user_image)
        tv_user_name.text = name
        tv_user_type.text = type
        tv_user_followers.text = (followersCount ?: 0).toString()
        tv_user_public_repos.text = (publicRepos ?: 0).toString()
        tv_user_public_gists.text = (publicGists ?: 0).toString()
    }

    private fun startWebBrowser() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/$username"))
        startActivity(browserIntent)
    }
}
