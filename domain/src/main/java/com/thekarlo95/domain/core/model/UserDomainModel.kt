package com.thekarlo95.domain.core.model

data class UserDomainModel (
        val id: Long,
        val name: String,
        val type: String,
        val avatarUrl: String,
        val publicRepos: Int?,
        val publicGists: Int?,
        val followersCount: Int?
)