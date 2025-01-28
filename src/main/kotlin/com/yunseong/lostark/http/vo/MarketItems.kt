package com.yunseong.lostark.http.vo

sealed class MarketItems {

    data class Request(
        override val bearerToken: String,
        val sort: String? = null,
        val categoryCode: String? = null,
        val characterClass: String? = null,
        val itemTier: Int = 4,
        val itemGrade: String? = null,
        val itemName: String? = null,
        val pageNo: Int = 0,
        val sortCondition: String = "ASC"
    ): RequestSpec(bearerToken)

    data class Response(
        val pageNo: Int,
        val pageSize: Int,
        val totalCount: Int
    ): ResponseSpec()
}