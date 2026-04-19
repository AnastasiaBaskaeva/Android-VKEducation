package com.android.vkeducation.baskaeva.data

import com.android.vkeducation.baskaeva.data.appdetails.AppDetailsDto
import com.android.vkeducation.baskaeva.data.applist.AppListItemDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {

    @GET("catalog")
    suspend fun getAppList(): List<AppListItemDto>
    @GET("catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto
}