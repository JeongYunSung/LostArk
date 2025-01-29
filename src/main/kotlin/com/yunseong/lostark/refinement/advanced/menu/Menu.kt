package com.yunseong.lostark.refinement.advanced.menu

import com.yunseong.lostark.database.InMemoryDatabase
import com.yunseong.lostark.refinement.advanced.core.*
import com.yunseong.lostark.refinement.advanced.core.AdvancedRefinement.*
import com.yunseong.lostark.refinement.advanced.vo.Level
import com.yunseong.lostark.refinement.advanced.vo.Level.L10
import com.yunseong.lostark.refinement.advanced.vo.Level.L20
import com.yunseong.lostark.vo.Materials

object Menu {

    private val originTier3Breath = listOf(
        Type.노숨,
        Type.가호,
        Type.가호축복,
        Type.풀숨
    )

    private val originTier4Breath = listOf(
        Type.노숨,
        Type.풀숨
    )

    private val newTier3Breath = mutableListOf(
        Type.노숨장인,
        Type.가호장인,
        Type.가호축복장인,
        Type.풀숨장인
    )

    private val newTier4Breath = mutableListOf(
        Type.노숨장인,
        Type.풀숨장인
    )

    init {
        newTier3Breath.addAll(originTier3Breath)
        newTier4Breath.addAll(originTier4Breath)
    }

    fun start() {
        setApiKey()
        InMemoryDatabase.pollingMarketPrice()
        val recipe = selectEquipment()
        selectFilterList(recipe).forEach(recipe::addFilter)
        val breathList = selectBreathList(recipe)

        val result = mutableListOf<Triple<Pair<Type, Type>, Pair<Double, Double>, Map<Materials,Int>>>()

        breathList.forEach { normal ->
            breathList.forEach { bonus ->
                val cnt = AdvancedRefinement.getExpectedTryCount(recipe.level, normal, bonus)
                val price = AdvancedRefinement.getExpectedPricePerTry(recipe, normal, bonus) * cnt
                val materials = AdvancedRefinement.getExpectedMaterials(recipe, cnt, normal, bonus)
                result.add(Triple(normal to bonus, cnt to price, materials))
            }
        }

        result.sortedBy {
            it.second.second
        }.forEachIndexed { index, it ->
            println("${index+1}. ${it.first.first} ${it.first.second} 시도 횟수: ${String.format("%.1f", it.second.first)}, 예상 가격: ${String.format("%.0f", it.second.second)}, ${it.third}")
        }
    }

    private fun setApiKey() {
        println("API 키를 입력해주세요.")
        InMemoryDatabase[InMemoryDatabase.API_KEY] = readln()
    }

    private fun selectEquipment(): AdvancedRefinementRecipe {
        println("티어 : 1.3티어, 2.4티어")
        val tier = readln()
        println("장비 : 1.방어구, 2.무기")
        val equipment = tier + readln()
        println("레벨 : 1.10, 2.20, 3,30, 4.40")
        val level = readln().toInt()

        val lv = Level.valueOf("L${level}0")

        return when(equipment) {
            "11" -> {
                Tier3Armor(lv)
            }
            "12" -> {
                Tier3Weapon(lv)
            }
            "21" -> {
                Tier4Armor(lv)
            }
            "22" -> {
                Tier4Weapon(lv)
            }
            else -> throw IllegalArgumentException("잘못된 입력입니다.")
        }
    }

    private fun selectFilterList(recipe: AdvancedRefinementRecipe): List<Materials> {
        val list = mutableListOf<Materials>()

        println("필터를 추가해주세요.")

        while(true) {
            val msg = when (recipe) {
                is Tier3Weapon -> {
                    "1.명예의 파편 2.최상급 오레하 융화 재료 3.찬란한 명예의 돌파석 4.정제된 파괴강석 \n5.태양의 축복 6.태양의 은총 7.태양의 가호 8.장인의 야금술1단계 0.종료"
                }

                is Tier3Armor ->  {
                    "1.명예의 파편 2.최상급 오레하 융화 재료 3.찬란한 명예의 돌파석 4.정제된 수호강석 \n5.태양의 축복 6.태양의 은총 7.태양의 가호 8.장인의 재봉술1단계 0.종료"
                }
                is Tier4Weapon -> {
                    "1.운명의 파편 2.아비도스 융화 재료 3.운명의 돌파석 4.운명의 파괴석 5.용암의 숨결" +
                        when (recipe.level) {
                            L10 -> " 6.장인의 야금술1단계 "
                            L20 -> " 6.장인의 야금술2단계 "
                            else -> " "
                        } + "0.종료"
                }

                is Tier4Armor -> {
                    "1.운명의 파편 2.아비도스 융화 재료 3.운명의 돌파석 4.운명의 수호석 5.빙하의 숨결" +
                        when (recipe.level) {
                            L10 -> " 6.장인의 재봉술1단계 "
                            L20 -> " 6.장인의 재봉술2단계 "
                            else -> " "
                        } + "0.종료"
                }
            }

            println(msg)

            val num = readln()

            if(num == "0") {
                break
            }

            val item = extractMaterial(msg, num.toInt()).replace("\n", "")
            val material = Materials.fromString(item.substring(0, item.length).replace(" ", "_"))!!

            if (list.contains(material)) {
                list.remove(material)
            }
            else {
                list.add(material)
            }
        }

        return list.toList()
    }

    private fun selectBreathList(recipe: AdvancedRefinementRecipe): List<Type> {
        return when (recipe) {
            is Tier3Weapon, is Tier3Armor -> {
                if (recipe.level == Level.L30 || recipe.level == Level.L40) {
                    originTier3Breath
                } else {
                    newTier3Breath
                }
            }
            is Tier4Weapon, is Tier4Armor -> {
                if (recipe.level == Level.L30 || recipe.level == Level.L40) {
                    originTier4Breath
                } else {
                    newTier4Breath
                }
            }
        }
    }

    private fun extractMaterial(input: String, index: Int): String {
        val regex = Regex("""$index\.(\D+\d*단계|\D+)""")
        val matchResult = regex.find(input)
        return matchResult?.groupValues?.get(1)?.trim() ?: ""
    }
}