package com.thekarlo95.search.ui.andorid.repositorydetail.model

import java.util.*

data class RepositoryUiModel(
        val id: Long,
        val name: String,
        val userAvatarUrl: String,
        val fullName: String,
        val description: String,
        val forksCount: Int,
        val starsCount: Int,
        val language: String,
        val createdAt: Date,
        val updatedAt: Date,
        val owner: UserUiModel,
        val license: String
)