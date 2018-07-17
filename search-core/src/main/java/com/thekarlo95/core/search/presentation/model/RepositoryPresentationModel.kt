package com.thekarlo95.core.search.presentation.model

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
        val ownerUserName: String,
        val license: String
)