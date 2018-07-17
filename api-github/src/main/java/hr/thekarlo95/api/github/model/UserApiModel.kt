package hr.thekarlo95.api.github.model

import com.google.gson.annotations.SerializedName

data class UserApiModel (
        @SerializedName("id") val id: Long,
        @SerializedName("login") val name: String,
        @SerializedName("type") val type: String,
        @SerializedName("avatar_url") val avatarUrl: String,
        @SerializedName("public_repos") val publicRepos: Int?,
        @SerializedName("public_gists") val publicGists: Int?,
        @SerializedName("followers") val followersCount: Int?
)