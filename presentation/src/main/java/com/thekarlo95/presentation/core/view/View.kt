package com.thekarlo95.presentation.core.view

interface View {

    fun showLoading()

    fun hideLoading()

    fun showError(errorMessage: String)
}