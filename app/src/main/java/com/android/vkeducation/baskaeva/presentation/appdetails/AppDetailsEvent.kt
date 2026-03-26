package com.android.vkeducation.baskaeva.presentation.appdetails

sealed interface AppDetailsEvent {
    data object UnderDevelopment : AppDetailsEvent
}