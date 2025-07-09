package com.zibro.gyeonggilocationcurrencyapp.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zibro.gyeonggilocationcurrencyapp.core.database.model.FavoriteStoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert(favorite: FavoriteStoreEntity)

    @Query("SELECT * FROM favorite")
    fun getAll(): Flow<List<FavoriteStoreEntity>>

    @Query("DELETE FROM favorite WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM favorite WHERE id = :id")
    fun getById(id: Int): Flow<FavoriteStoreEntity?>
}