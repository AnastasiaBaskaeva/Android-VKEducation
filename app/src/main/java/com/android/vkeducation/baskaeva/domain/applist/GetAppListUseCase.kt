package com.android.vkeducation.baskaeva.domain.applist

class GetAppListUseCase(
    private val appListRepository: AppListRepository
) {
    suspend operator fun invoke(): List<AppListItem> {
        return appListRepository.getAppList()

    }
}