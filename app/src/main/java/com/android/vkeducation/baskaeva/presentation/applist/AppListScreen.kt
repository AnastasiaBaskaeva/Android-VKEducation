package com.android.vkeducation.baskaeva.presentation.applist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppListScreen(
    onAppClick: (appId: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        AppListToolbar()
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(
                items = hardcodedAppList,
                key = { app -> app.id + app.name },
            ) { app ->
                AppListItem(
                    app = app,
                    onClick = onAppClick,
                )
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    color = Color.Transparent,
                )
            }
        }
    }
}