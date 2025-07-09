package com.zibro.gyeonggilocationcurrencyapp.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 즐겨찾는 지역화폐 가맹점
 *
 * @param id 가맹점번호
 * @param name 가맹점명
 * @param latitude 위도
 * @param longitude 경도
 * @param addressJibun 지번주소
 * @param addressRoad 도로명주소
 * @param industryType 업종명
 */
@Entity(
    tableName = "favorite"
)
data class FavoriteStoreEntity(
    @PrimaryKey
    val id: Int,
    val name : String,
    val latitude : Double,
    val longitude : Double,
    @ColumnInfo(name = "address_jibun")
    val addressJibun : String,
    @ColumnInfo(name = "address_road")
    val addressRoad : String,
    @ColumnInfo(name = "industry_type")
    val industryType : String
)

// TODO: core:model 모듈 data 디렉토리에 model로 변환하는 mapper 함수 제작
fun FavoriteStoreEntity.asExternalModel() {}