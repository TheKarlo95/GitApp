package com.thekarlo95.presentation.core.model

import java.util.*

data class RepositoryPresentationModel(
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
        val owner: UserPresentationModel,
        val license: String
)