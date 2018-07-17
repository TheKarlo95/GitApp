package com.thekarlo95.search.ui.andorid.user.model

data class UserUiModel (
        val id: Long,
        val name: String,
        val type: String,
        val avatarUrl: String,
        val publicRepos: Int?,
        val publicGists: Int?,
        val followersCount: Int?
)