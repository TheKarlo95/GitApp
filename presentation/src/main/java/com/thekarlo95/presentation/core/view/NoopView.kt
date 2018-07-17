package com.thekarlo95.presentation.core.view

open class NoopView : View {

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showError(errorMessage: String) {}
}