package com.zibro.gyeonggilocationcurrencyapp.core.network

interface NetworkDataSource {

    suspend fun getStoreByLatLng(latitude : Double, longitude : Double) : List<Any>

    suspend fun getStoreByName(name : String) : List<Any>

    suspend fun getStoreByIndustryType(industryType : String) : List<Any>
}