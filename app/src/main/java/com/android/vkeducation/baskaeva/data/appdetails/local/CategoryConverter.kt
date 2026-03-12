package io.mmaltsev.vkeducation.data.appdetails.local

import androidx.room.TypeConverter
import com.android.vkeducation.baskaeva.domain.appdetails.Category

class CategoryConverter {
    @TypeConverter
    fun fromCategory(category: Category): String = category.name

    @TypeConverter
    fun toCategory(categoryName: String): Category = Category.valueOf(categoryName)
}