package hr.thekarlo95.api.github.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class RepositoryApiModel(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("full_name") val fullName: String,
        @SerializedName("description") val description: String?,
        @SerializedName("forks_count") val forksCount: Int,
        @SerializedName("stargazers_count") val starsCount: Int,
        @SerializedName("language") val language: String?,
        @SerializedName("created_at") val createdAt: Date,
        @SerializedName("updated_at") val updatedAt: Date,
        @SerializedName("owner") val owner: UserApiModel,
        @SerializedName("license") val license: LicenseApiModel?
)