package com.zibro.gyeonggilocationcurrencyapp.core.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.zibro.gyeonggilocationcurrencyapp.core.database.AppDatabase
import org.junit.After
import org.junit.Before

internal abstract class DatabaseTest {
    private lateinit var db: AppDatabase
    protected lateinit var favoriteDao: FavoriteDao

    @Before
    fun setup() {
        db = run {
            val context = ApplicationProvider.getApplicationContext<Context>()
            Room.inMemoryDatabaseBuilder(
                context,
                AppDatabase::class.java,
            ).build()
        }
        favoriteDao = db.favoriteDao()
    }

    @After
    fun teardown() = db.close()
}