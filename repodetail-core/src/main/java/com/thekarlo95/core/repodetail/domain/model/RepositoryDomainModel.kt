package com.thekarlo95.core.repodetail.domain.model

import java.util.*

data class RepositoryDomainModel(
        val id: Long,
        val name: String,
        val fullName: String,
        val description: String,
        val forksCount: Int,
        val starsCount: Int,
        val language: String,
        val createdAt: Date,
        val updatedAt: Date,
        val owner: UserDomainModel,
        val license: LicenseDomainModel
)