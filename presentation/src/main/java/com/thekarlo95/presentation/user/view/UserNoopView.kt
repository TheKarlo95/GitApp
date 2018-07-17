package com.thekarlo95.presentation.user.view

import com.thekarlo95.presentation.core.model.RepositoryPresentationModel
import com.thekarlo95.presentation.core.model.UserPresentationModel
import com.thekarlo95.presentation.core.view.NoopView

object UserNoopView : NoopView(), UserView {
    override fun showUser(user: UserPresentationModel) {
    }
}