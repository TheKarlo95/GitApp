package com.thekarlo95.search.ui.andorid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import com.thekarlo95.search.ui.andorid.repositorydetail.RepositoryDetailFragment
import com.thekarlo95.search.ui.andorid.respositorysearch.RepositorySearchFragment
import com.thekarlo95.search.ui.andorid.user.UserFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    companion object {
        const val FRAGMENT_TAG = "fragment"
    }

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    private val isTwoPane by lazy {
        val detailFragment = findViewById<FrameLayout>(R.id.fragment_detail)
        detailFragment != null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        RepositorySearchFragment.create().addFragmentToMaster()
    }


    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    fun startRepositoryDetailFragment(owner: String, repository: String) {
        RepositoryDetailFragment.create(owner, repository)
                .addFragmentToDetail()
    }

    fun startUserFragment(username: String) {
        UserFragment.create(username)
                .addFragmentToDetail()
    }

    fun startWebBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    private fun Fragment.addFragmentToMaster() {
        this.addFragment(R.id.fragment_master)
    }

    private fun Fragment.addFragmentToDetail() {
        this.addFragment(R.id.fragment_detail)
    }

    private fun Fragment.addFragment(@IdRes id: Int) {
        if (isTwoPane) {
            supportFragmentManager.beginTransaction().apply {
                replace(id, this@addFragment)
                addToBackStack(FRAGMENT_TAG)
                commit()
            }
        } else {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, this@addFragment)
                addToBackStack(FRAGMENT_TAG)
                commit()
            }
        }
    }
}