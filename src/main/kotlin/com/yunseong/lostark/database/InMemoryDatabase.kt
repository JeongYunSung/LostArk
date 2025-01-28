package com.yunseong.lostark.database

import com.yunseong.lostark.Main
import com.yunseong.lostark.http.core.LostArkService
import com.yunseong.lostark.http.vo.MarketItems
import com.yunseong.lostark.vo.Materials

object InMemoryDatabase {

    private val database = mutableMapOf<Any, Any>()

    init {
        database[Materials.골드] = 1.0
        database[Materials.Blank] = 0.0

        refreshPrice(3)
        refreshPrice(4)
    }

    operator fun <T> get(key: Any, clazz: Class<T>): T {
        return clazz.cast(database[key]!!)
    }

    operator fun <T: Any> set(key: String, value: T) {
        database[key] = value
    }

    private fun refreshPrice(tier: Int) {
        var pageNo = 0
        var repeat: Boolean

        do {
            val response = LostArkService.getItemList(
                MarketItems.Request(
                    bearerToken = Main.API_KEY,
                    categoryCode = 50000,
                    itemTier = tier,
                    pageNo = pageNo
                )
            )
            repeat = (response.totalCount / response.pageSize) > pageNo++

            response.items.filter {
                Materials.fromString(it.name) != null
            }.forEach {
                when (it.name) {
                    "명예의 파편 주머니(소)", "운명의 파편 주머니(소)" -> {
                        database[Materials.fromString(it.name)!!] = String.format("%.4f", it.avgPrice / 1000).toDouble()
                    }
                    else -> {
                        database[Materials.fromString(it.name)!!] = it.avgPrice
                    }
                }
            }

        } while (repeat)
    }
}