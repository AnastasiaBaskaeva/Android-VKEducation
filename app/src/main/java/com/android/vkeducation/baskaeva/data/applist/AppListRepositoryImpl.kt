package com.android.vkeducation.baskaeva.data.applist

import com.android.vkeducation.baskaeva.data.AppApi
import com.android.vkeducation.baskaeva.domain.applist.AppListItem
import com.android.vkeducation.baskaeva.domain.applist.AppListRepository
import javax.inject.Inject
import kotlin.collections.map

class AppListRepositoryImpl @Inject constructor(
    private val appApi: AppApi,
    private val mapper: AppListMapper
) : AppListRepository {

    override suspend fun getAppList(): List<AppListItem> {
        return appApi.getAppList().map { mapper.toDomain(it) }
    }
}