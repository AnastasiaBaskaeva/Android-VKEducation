package com.android.vkeducation.baskaeva.data.appdetails.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.mmaltsev.vkeducation.data.appdetails.local.AppDetailsDao
import io.mmaltsev.vkeducation.data.appdetails.local.AppDetailsEntity
import io.mmaltsev.vkeducation.data.appdetails.local.CategoryConverter

@Database(
    entities = [AppDetailsEntity::class],
    version = 1,
)
@TypeConverters(CategoryConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDetailsDao(): AppDetailsDao

    companion object {
        const val DATABASE_NAME = "app_database"
    }

}