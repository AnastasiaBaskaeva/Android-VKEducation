package com.android.vkeducation.baskaeva.presentation.applist

sealed class AppListEvent {
    data class ShowSnackbar(val message: String) : AppListEvent()
}