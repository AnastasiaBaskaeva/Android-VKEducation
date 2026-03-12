package com.android.vkeducation.baskaeva.presentation.navigation

sealed class Screen(val route: String) {
    object AppList : Screen("app_list")
    object AppDetails : Screen("app_details/{appId}") {
        fun createRoute(appId: String) = "app_details/$appId"
    }
}