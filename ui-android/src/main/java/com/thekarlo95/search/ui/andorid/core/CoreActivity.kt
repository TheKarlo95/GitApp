package com.thekarlo95.search.ui.andorid.core

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.thekarlo95.presentation.core.view.View
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class CoreActivity : AppCompatActivity(), View {

    private val compositeDisposable = CompositeDisposable()

    protected fun Disposable.addDisposable() = compositeDisposable.add(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}