package hr.thekarlo95.api.github.model

import com.google.gson.annotations.SerializedName

data class LicenseApiModel(
        @SerializedName("key") val key: String,
        @SerializedName("name") val name: String
)