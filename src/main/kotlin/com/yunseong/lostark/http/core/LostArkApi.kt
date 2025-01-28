package com.yunseong.lostark.http.core

import com.yunseong.lostark.http.vo.MarketItemDetail
import com.yunseong.lostark.http.vo.MarketItems
import retrofit2.Call
import retrofit2.http.*

interface LostArkApi {

    @GET("markets/items/{itemId}")
    fun itemDetails(
        @Header("Authorization") authorization: String,
        @Path("itemId") itemId: Int
    ): Call<List<MarketItemDetail.Response>>

    @POST("markets/items")
    fun itemList(
        @Header("Authorization") authorization: String,
        @Body request: MarketItems.Request
    ): Call<MarketItems.Response>
}