package com.thekarlo95.search.ui.andorid.core

import android.support.v4.app.Fragment
import android.widget.Toast
import com.thekarlo95.presentation.core.presenter.Presenter
import com.thekarlo95.presentation.core.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class CoreFragment<P : Presenter> : Fragment(), View {

    private val compositeDisposable = CompositeDisposable()

    protected fun Disposable.addDisposable() = compositeDisposable.add(this)

    abstract var presenter: P

    override fun onDestroy() {
        presenter.destroy()
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }
}