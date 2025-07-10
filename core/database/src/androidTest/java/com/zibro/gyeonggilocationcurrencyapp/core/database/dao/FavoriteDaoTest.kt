package com.zibro.gyeonggilocationcurrencyapp.core.database.dao

import android.util.Log
import app.cash.turbine.test
import com.zibro.gyeonggilocationcurrencyapp.core.database.model.FavoriteStoreEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

internal class FavoriteDaoTest : DatabaseTest() {

    /**
     * 단일 insert
     */
    @Test
    fun insert_ShouldAddNewFavorite() = runTest {
        // given
        val favorite = FavoriteStoreEntity(
            id = 1,
            name = "Test Store",
            latitude = 37.0,
            longitude = 127.0,
            addressJibun = "Test Address Jibun",
            addressRoad = "Test Address Road",
            industryType = "음식점"
        )

        // when
        favoriteDao.insert(favorite)

        Log.d("zibro", "insert_ShouldAddNewFavorite: ${favorite}")

        // then
        val result = favoriteDao.getAll().first()
        assertEquals(1, result.size)
        assertEquals(favorite, result[0])
    }

    /**
     * 다중 insert
     */
    @Test
    fun getAll_ShouldReturnAllFavorites() = runTest {
        // given
        val favorites = listOf(
            FavoriteStoreEntity(
                id = 1,
                name = "Store 1",
                latitude = 37.0,
                longitude = 127.0,
                addressJibun = "Address Jibun 1",
                addressRoad = "Address Road 1",
                industryType = "음식점"
            ),
            FavoriteStoreEntity(
                id = 2,
                name = "Store 2",
                latitude = 37.1,
                longitude = 127.1,
                addressJibun = "Address Jibun 2",
                addressRoad = "Address Road 2",
                industryType = "카페"
            )
        )
        favorites.forEach { favoriteDao.insert(it) }

        // when & then
        favoriteDao.getAll().test {
            val result = awaitItem()
            assertEquals(2, result.size)
            assertEquals(favorites, result)
            cancelAndIgnoreRemainingEvents()
        }
    }

    /**
     * 삭제
     */
    @Test
    fun delete_ShouldRemoveFavorite() = runTest {
        // given
        val favorite = FavoriteStoreEntity(
            id = 1,
            name = "Store",
            latitude = 37.0,
            longitude = 127.0,
            addressJibun = "Address Jibun",
            addressRoad = "Address Road",
            industryType = "음식점"
        )
        favoriteDao.insert(favorite)

        // when
        favoriteDao.delete(1)

        // then
        val result = favoriteDao.getAll().first()
        assertEquals(0, result.size)
    }

    /**
     * 다중 조회
     */
    @Test
    fun getAll_ShouldReturnCorrectFavorite() = runTest {
        // given
        val favorite = FavoriteStoreEntity(
            id = 1,
            name = "Store",
            latitude = 37.0,
            longitude = 127.0,
            addressJibun = "Address Jibun",
            addressRoad = "Address Road",
            industryType = "음식점"
        )
        favoriteDao.insert(favorite)

        // when & then
        favoriteDao.getAll().test {
            val result = awaitItem()
            assertEquals(1, result.size)
            assertEquals(favorite, result[0])
            cancelAndIgnoreRemainingEvents()
        }
    }

}