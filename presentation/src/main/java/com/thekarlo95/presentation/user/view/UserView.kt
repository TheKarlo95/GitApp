package com.thekarlo95.presentation.user.view

import com.thekarlo95.presentation.core.model.UserPresentationModel
import com.thekarlo95.presentation.core.view.View

interface UserView : View {

    fun showUser(user: UserPresentationModel)
}