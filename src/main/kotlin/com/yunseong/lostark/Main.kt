package com.yunseong.lostark

import com.yunseong.lostark.AdvancedRefinement.*
import com.yunseong.lostark.Level.*
import com.yunseong.lostark.Materials.*

fun main() {
        val list = listOf(
                Type.노숨 to Type.노숨,
                Type.노숨 to Type.풀숨,
                Type.풀숨 to Type.노숨,
                Type.풀숨 to Type.풀숨
        )

        val filterList = listOf(운명의_파편, 운명의_돌파석, 운명의_수호석, 빙하의_숨결, 운명의_파괴석)

        println("4티어 30단계 무기=================")
        show(list, Tier4Weapon(L30, filterList), Tier4)

        println("4티어 40단계 무기=================")
        show(list, Tier4Weapon(L40, filterList), Tier4)

//        println("4티어 30단계 방어구=================")
//        show(list, Tier4Armor(L30, filterList), Tier4)
//
//        println("4티어 40단계 방어구=================")
//        show(list, Tier4Armor(L40, filterList), Tier4)
}

fun show(list: List<Pair<Type, Type>>, recipe: AdvancedRefinementRecipe, advancedRefinement: AdvancedRefinement) {
        list.map {
                val cnt = AdvancedRefinement.getExpectedTryCount(advancedRefinement, recipe.level, it.first, it.second)
                val price = AdvancedRefinement.getExpectedPricePerTry(recipe, it.first, it.second) * cnt
                Triple(it.first to it.second, cnt, price)
        }.sortedBy {
                it.third
        }.forEach {
                println("${it.first.first} ${it.first.second} 시도 횟수: ${String.format("%.1f", it.second)}, 예상 가격: ${String.format("%.0f", it.third)}")
        }
}