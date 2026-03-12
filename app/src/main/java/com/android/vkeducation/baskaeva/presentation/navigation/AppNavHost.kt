package com.android.vkeducation.baskaeva.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.vkeducation.baskaeva.presentation.appdetails.AppDetailsScreen
import com.android.vkeducation.baskaeva.presentation.applist.AppListScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.AppList.route,
        modifier = modifier,
    ) {
        composable(route = Screen.AppList.route) {
            AppListScreen(
                onAppClick = { appId ->
                    navController.navigate(Screen.AppDetails.createRoute(appId))
                },
            )
        }

        composable(
            route = Screen.AppDetails.route,
            arguments = listOf(
                navArgument("appId") { type = NavType.StringType }
            ),
        ) {
            AppDetailsScreen(
                onBackClick = { navController.popBackStack() },
            )
        }
    }
}