package com.android.vkeducation.baskaeva.domain.applist

interface AppListRepository{

    suspend fun getAppList(): List<AppListItem>
}