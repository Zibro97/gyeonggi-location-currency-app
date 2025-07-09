package com.zibro.gyeonggilocationcurrencyapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zibro.gyeonggilocationcurrencyapp.core.database.dao.FavoriteDao
import com.zibro.gyeonggilocationcurrencyapp.core.database.model.FavoriteStoreEntity

@Database(
    entities = [
        FavoriteStoreEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao() : FavoriteDao
}
