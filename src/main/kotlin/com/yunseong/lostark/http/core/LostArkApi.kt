package com.yunseong.lostark.http.core

import com.yunseong.lostark.http.vo.MarketItemDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface LostArkApi {

    @GET("markets/items/{itemId}")
    fun itemDetails(
        @Header("Authorization") authorization: String,
        @Path("itemId") itemId: Int
    ): Call<List<MarketItemDetail.Response>>
}