package com.android.vkeducation.baskaeva.data.applist

import com.android.vkeducation.baskaeva.domain.applist.AppListItem

class AppListMapper {

    fun toDomain(dto: AppListItemDto): AppListItem = AppListItem(
        id = dto.id,
        name = dto.name,
        description = dto.description,
        category = dto.category,
        icon = dto.icon
    )
}