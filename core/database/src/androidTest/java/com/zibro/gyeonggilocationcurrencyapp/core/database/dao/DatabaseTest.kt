package com.zibro.gyeonggilocationcurrencyapp.core.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.zibro.gyeonggilocationcurrencyapp.core.database.AppDatabase
import org.junit.After
import org.junit.Before

abstract class DatabaseTest {
    protected lateinit var db: AppDatabase
    protected lateinit var favoriteDao: FavoriteDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        favoriteDao = db.favoriteDao()
    }

    @After
    fun tearDown() {
        db.close()
    }
}
