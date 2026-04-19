package com.android.vkeducation.baskaeva.presentation.applist

import com.android.vkeducation.baskaeva.domain.applist.AppListItem

data class AppListState(
    val apps: List<AppListItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)