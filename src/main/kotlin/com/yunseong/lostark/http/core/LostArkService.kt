package com.yunseong.lostark.http.core

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.yunseong.lostark.http.exception.LostArkException
import com.yunseong.lostark.http.vo.MarketItemDetail
import com.yunseong.lostark.http.vo.MarketItems
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object LostArkService {

    private val objectMapper = ObjectMapper()
        .registerModules(JavaTimeModule())

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://developer-lostark.game.onstove.com")
        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
        .build()
        .create(LostArkApi::class.java)

    fun getItemDetails(request: MarketItemDetail.Request): List<MarketItemDetail.Response> {
        return retrofit.itemDetails("bearer ${request.bearerToken}", request.itemId).execute().body() ?: throw LostArkException("조회되는 결과가 없습니다.")
    }

    fun getItemList(request: MarketItems.Request): MarketItems.Response {
        return retrofit.itemList("bearer ${request.bearerToken}", request).execute().body() ?: throw LostArkException("조회되는 결과가 없습니다.")
    }
}