package com.yunseong.lostark.http.vo

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

sealed class MarketItems {

    data class Request(
        @JsonIgnore
        override val bearerToken: String,
        val sort: String? = null,
        val categoryCode: Int? = null,
        val characterClass: String? = null,
        val itemTier: Int = 4,
        val itemGrade: String? = null,
        val itemName: String? = null,
        val pageNo: Int = 0,
        val sortCondition: String = "ASC"
    ) : RequestSpec(bearerToken)

    data class Response @JsonCreator constructor(
        @JsonProperty("PageNo") val pageNo: Int,
        @JsonProperty("PageSize") val pageSize: Int,
        @JsonProperty("TotalCount") val totalCount: Int,
        @JsonProperty("Items") val items: List<Items>
    ) : ResponseSpec()

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Items @JsonCreator constructor(
        @JsonProperty("Id") val id: Long,
        @JsonProperty("Name") val name: String,
        @JsonProperty("YDayAvgPrice") val avgPrice: Double
    )
}