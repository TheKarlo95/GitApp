package com.thekarlo95.presentation.user.presenter

import com.thekarlo95.presentation.core.presenter.Presenter
import com.thekarlo95.presentation.user.view.UserView

interface UserPresenter : Presenter {

    var view: UserView

    fun loadUser(username: String)
}