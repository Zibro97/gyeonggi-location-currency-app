package com.zibro.gyeonggilocationcurrencyapp.core.database.di

import com.zibro.gyeonggilocationcurrencyapp.core.database.AppDatabase
import com.zibro.gyeonggilocationcurrencyapp.core.database.dao.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesFavoriteDao(
        database : AppDatabase
    ) : FavoriteDao = database.favoriteDao()
}