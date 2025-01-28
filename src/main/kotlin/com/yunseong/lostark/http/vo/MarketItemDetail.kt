package com.yunseong.lostark.http.vo

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

sealed class MarketItemDetail {

    data class Request(
        override val bearerToken: String,
        val itemId: Int
    ): RequestSpec(bearerToken)

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Response @JsonCreator constructor(
        @JsonProperty("Name") val name: String?,
        @JsonProperty("BundleCount") val bundleCount: Int?,
        @JsonProperty("Stats") val stats: List<Stats>?
    ): ResponseSpec()

    data class Stats @JsonCreator constructor(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @JsonProperty("Date") val date: LocalDate?,
        @JsonProperty("AvgPrice") val avgPrice: Double?,
        @JsonProperty("TradeCount") val tradeCount: Int?
    )
}