package com.android.vkeducation.baskaeva.data.applist

import com.android.vkeducation.baskaeva.data.appdetails.AppDetailsDto
import com.android.vkeducation.baskaeva.domain.appdetails.AppDetails
import com.android.vkeducation.baskaeva.domain.applist.AppListItem
import kotlin.String

class AppListMapper {

    fun toDomain(dto: AppListItemDto): AppListItem = AppListItem(
        id = dto.id,
        name = dto.name,
        subtitle = dto.subtitle,
        category = dto.category,
        icon = dto.icon
    )
}