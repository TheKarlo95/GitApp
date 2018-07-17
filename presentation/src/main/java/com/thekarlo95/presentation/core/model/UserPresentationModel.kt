package com.thekarlo95.presentation.core.model

data class UserPresentationModel (
        val id: Long,
        val name: String,
        val type: String,
        val avatarUrl: String,
        val publicRepos: Int?,
        val publicGists: Int?,
        val followersCount: Int?
)