package com.android.vkeducation.baskaeva.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import com.android.vkeducation.baskaeva.presentation.navigation.AppNavHost
import com.android.vkeducation.baskaeva.presentation.theme.VkEducationTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = false
        setContent {
            VkEducationTheme {
                AppNavHost(modifier = Modifier.fillMaxSize())
            }
        }
    }
}