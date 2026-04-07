package com.android.vkeducation.baskaeva.data.appdetails.local


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [AppDetailsEntity::class],
    version = 2,
)
@TypeConverters(CategoryConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDetailsDao(): AppDetailsDao

    companion object {
        const val DATABASE_NAME = "app_database"
    }

}