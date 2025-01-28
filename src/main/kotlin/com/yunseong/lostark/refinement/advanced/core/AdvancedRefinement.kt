package com.yunseong.lostark.refinement.advanced.core

import com.yunseong.lostark.database.InMemoryDatabase
import com.yunseong.lostark.refinement.advanced.core.AdvancedRefinement.Type.가호
import com.yunseong.lostark.refinement.advanced.core.AdvancedRefinement.Type.가호장인
import com.yunseong.lostark.refinement.advanced.core.AdvancedRefinement.Type.가호축복
import com.yunseong.lostark.refinement.advanced.core.AdvancedRefinement.Type.가호축복장인
import com.yunseong.lostark.refinement.advanced.core.AdvancedRefinement.Type.노숨
import com.yunseong.lostark.refinement.advanced.core.AdvancedRefinement.Type.노숨장인
import com.yunseong.lostark.refinement.advanced.core.AdvancedRefinement.Type.풀숨
import com.yunseong.lostark.refinement.advanced.core.AdvancedRefinement.Type.풀숨장인
import com.yunseong.lostark.refinement.advanced.vo.Ancestors.갈라투르의망치
import com.yunseong.lostark.refinement.advanced.vo.Ancestors.겔라르의칼
import com.yunseong.lostark.refinement.advanced.vo.Ancestors.나베르의송곳
import com.yunseong.lostark.refinement.advanced.vo.Ancestors.에베르의끌
import com.yunseong.lostark.refinement.advanced.vo.Ancestors.쿠훔바르의모루
import com.yunseong.lostark.refinement.advanced.vo.Ancestors.테메르의정
import com.yunseong.lostark.refinement.advanced.vo.Level.*
import com.yunseong.lostark.vo.Materials
import com.yunseong.lostark.vo.Materials.Blank
import com.yunseong.lostark.vo.Materials.빙하의_숨결
import com.yunseong.lostark.vo.Materials.용암의_숨결
import com.yunseong.lostark.vo.Materials.장인의_야금술1단계
import com.yunseong.lostark.vo.Materials.장인의_야금술2단계
import com.yunseong.lostark.vo.Materials.장인의_재봉술1단계
import com.yunseong.lostark.vo.Materials.장인의_재봉술2단계
import com.yunseong.lostark.vo.Materials.태양의_가호
import com.yunseong.lostark.vo.Materials.태양의_은총
import com.yunseong.lostark.vo.Materials.태양의_축복
import com.yunseong.lostark.refinement.advanced.vo.Level

sealed class AdvancedRefinement {

    enum class Type(val materials: List<Materials>) {
        노숨(listOf(Blank)),
        노숨장인(listOf(장인의_야금술1단계, 장인의_야금술2단계, 장인의_재봉술1단계, 장인의_재봉술2단계)),
        가호(listOf(태양의_가호)),
        가호장인(가호.materials + 노숨장인.materials),
        가호축복(listOf(태양의_가호, 태양의_축복)),
        가호축복장인(가호축복.materials + 노숨장인.materials),
        풀숨(listOf(태양의_가호, 태양의_축복, 태양의_은총, 용암의_숨결, 빙하의_숨결)),
        풀숨장인(풀숨.materials + 노숨장인.materials)
    }

    data object Tier3 : AdvancedRefinement() {

        private val successTable = mapOf(
            노숨 to arrayOf(0.8, 0.15, 0.05),
            노숨장인 to arrayOf(0.3, 0.45, 0.25),
            가호 to arrayOf(0.7, 0.2, 0.1),
            가호장인 to arrayOf(0.2, 0.5, 0.3),
            가호축복 to arrayOf(0.6, 0.25, 0.15),
            가호축복장인 to arrayOf(0.1, 0.55, 0.35),
            풀숨 to arrayOf(0.5, 0.3, 0.2),
            풀숨장인 to arrayOf(0.0, 0.6, 0.4),
        )

        override fun getAverageNormalExp(type: Type): Double {
            return successTable[type]!![0] * 10 + successTable[type]!![1] * 20 + successTable[type]!![2] * 40
        }
    }

    data object Tier4 : AdvancedRefinement() {

        private val successTable = mapOf(
            노숨 to arrayOf(0.8, 0.15, 0.05),
            노숨장인 to arrayOf(0.3, 0.45, 0.25),
            풀숨 to arrayOf(0.5, 0.3, 0.2),
            풀숨장인 to arrayOf(0.0, 0.6, 0.4),
        )

        override fun getAverageNormalExp(type: Type): Double {
            return successTable[type]!![0] * 10 + successTable[type]!![1] * 20 + successTable[type]!![2] * 40
        }
    }

    fun getAverageBonusExp(level: Level, type: Type): Double {
        val base = getAverageNormalExp(type)

        return when (level) {
            L10, L20 -> 테메르의정[base] * 0.35 + 쿠훔바르의모루[base] * 0.15 + 겔라르의칼[base] * 0.35 + 갈라투르의망치[base] * 0.15
            else -> 테메르의정[base] * 0.25 + 쿠훔바르의모루[base] * 0.125 + 겔라르의칼[base] * 0.25 + 갈라투르의망치[base] * 0.125 + 나베르의송곳[base] * 0.125 + 에베르의끌[base] * 0.125
        }
    }

    abstract fun getAverageNormalExp(type: Type): Double

    companion object {

        private const val FREE_RATE = (1 / 6.0) * 0.35
        private const val BONUS_RATE = 0.16106
        private const val TOTAL_EXP = 1000

        fun getExpectedTryCount(
            advancedRefinement: AdvancedRefinement,
            level: Level,
            normalType: Type,
            bonusType: Type
        ): Double {
            val exp =
                advancedRefinement.getAverageNormalExp(normalType) * (1 - BONUS_RATE) + advancedRefinement.getAverageBonusExp(
                    level,
                    bonusType
                ) * BONUS_RATE
            return TOTAL_EXP / exp
        }

        fun getExpectedPricePerTry(recipe: AdvancedRefinementRecipe, normalType: Type, bonusType: Type): Double {
            val basePrice = recipe.requiredMaterialsTable().map {
                InMemoryDatabase.get<Double>(it.key) * it.value
            }.sum()

            val normalPrice = basePrice * (1 - FREE_RATE) + recipe.requiredAdditionalMaterialsTable().filter {
                it.key in normalType.materials
            }.map {
                InMemoryDatabase.get<Double>(it.key) * it.value
            }.sum()

            val bonusPrice = basePrice + recipe.requiredAdditionalMaterialsTable().filter {
                it.key in bonusType.materials
            }.map {
                InMemoryDatabase.get<Double>(it.key) * it.value
            }.sum()

            return normalPrice * (1 - BONUS_RATE) + bonusPrice * BONUS_RATE
        }
    }
}