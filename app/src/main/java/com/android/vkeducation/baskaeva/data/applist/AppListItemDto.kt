package com.android.vkeducation.baskaeva.data.applist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppListItemDto(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    @SerialName("iconUrl") val icon: String
)