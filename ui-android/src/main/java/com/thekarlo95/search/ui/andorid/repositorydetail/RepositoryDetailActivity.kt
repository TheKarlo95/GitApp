package com.thekarlo95.search.ui.andorid.repositorydetail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.thekarlo95.presentation.core.model.RepositoryPresentationModel
import com.thekarlo95.presentation.repodetail.presenter.RepositoryDetailPresenter
import com.thekarlo95.presentation.repodetail.view.RepositoryDetailView
import com.thekarlo95.search.ui.andorid.R
import com.thekarlo95.search.ui.andorid.core.CoreActivity
import com.thekarlo95.search.ui.andorid.repositorydetail.mapper.RepositoryUiMapper
import com.thekarlo95.search.ui.andorid.user.UserActivity
import kotlinx.android.synthetic.main.activity_repository_detail.*
import kotlinx.android.synthetic.main.content_repository_detail.*
import java.text.DateFormat
import javax.inject.Inject

class RepositoryDetailActivity : CoreActivity(), RepositoryDetailView {

    companion object {
        val DATE_FORMAT: DateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
        const val OWNER_EXTRA = "owner_name"
        const val REPOSITORY_EXTRA = "repository_name"

        fun createIntent(context: Context, ownerName: String, repositoryName: String): Intent {
            return Intent(context, RepositoryDetailActivity::class.java)
                    .putExtra(OWNER_EXTRA, ownerName)
                    .putExtra(REPOSITORY_EXTRA, repositoryName)
        }
    }

    @Inject
    lateinit var mapper: RepositoryUiMapper
    @Inject
    lateinit var presenter: RepositoryDetailPresenter

    private var owner: String = ""
    private var repo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_detail)
        setSupportActionBar(toolbar)

        presenter.view = this

        owner = intent.getStringExtra(OWNER_EXTRA) ?: ""
        repo = intent.getStringExtra(REPOSITORY_EXTRA) ?: ""
        presenter.loadRepository(owner, repo)
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

    @SuppressLint("SetTextI18n")
    override fun showRepository(repository: RepositoryPresentationModel) = with(mapper.toUi(repository)) {
        toolbar.title = fullName
        Glide.with(this@RepositoryDetailActivity).load(userAvatarUrl).into(iv_repository_image)
        tv_repository.text = fullName
        tv_created_at.text = DATE_FORMAT.format(createdAt)
        tv_updated_at.text = DATE_FORMAT.format(updatedAt)
        tv_description.text = description
        tv_user_name.text = owner.name
        but_user_info.text = "More about ${owner.name}"
        but_user_info.setOnClickListener {
            startActivity(UserActivity.createIntent(this@RepositoryDetailActivity, owner.name))
        }
    }

    private fun startWebBrowser() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/$owner/$repo"))
        startActivity(browserIntent)
    }
}
