package com.thekarlo95.data.model

import java.util.*

data class RepositoryDataModel(
        val id: Long,
        val name: String,
        val fullName: String,
        val description: String,
        val forksCount: Int,
        val starsCount: Int,
        val language: String,
        val createdAt: Date,
        val updatedAt: Date,
        val owner: UserDataModel,
        val license: LicenseDataModel
)