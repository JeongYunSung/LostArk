package com.yunseong.lostark

import com.yunseong.lostark.Level.*
import com.yunseong.lostark.Materials.*

sealed class AdvancedRefinementRecipe(level: Level, private val filterList: List<Materials> = emptyList()) {
        fun requiredMaterialsTable(): Map<Materials, Int> {
                return internalRequiredMaterialsTable.filterKeys { !filterList.contains(it) }
        }
        
        abstract val internalRequiredMaterialsTable: Map<Materials, Int>
        
        abstract val requiredAdditionalMaterialsTable: Map<Materials, Int>
}

class Tier3Weapon(level: Level,  filterList: List<Materials> = emptyList()): AdvancedRefinementRecipe(level, filterList) {
        override val internalRequiredMaterialsTable = when (level) {
                L10 -> mapOf(
                        정제된_파괴강석 to 1000,
                        찬란한_명예의_돌파석 to 28,
                        최상급_오레하_융화제 to 30,
                        명예의_파편 to 9000,
                        골드 to 1125
                )
                
                else -> mapOf()
        }
        
        override val requiredAdditionalMaterialsTable = mapOf(
                태양의_축복 to 24,
                태양의_은총 to 12,
                태양의_가호 to 4,
                장인의_야금술1 to 1
        )
}

class Tier3Armor(level: Level,  filterList: List<Materials> = emptyList()): AdvancedRefinementRecipe(level, filterList) {
        override val internalRequiredMaterialsTable = when(level) {
                L10 -> mapOf(
                        정제된_수호강석 to 950,
                        찬란한_명예의_돌파석 to 22,
                        최상급_오레하_융화제 to 18,
                        명예의_파편 to 5500,
                        골드 to 950
                )
                else -> mapOf()
        }
        
        override val requiredAdditionalMaterialsTable = mapOf(
                태양의_축복 to 24,
                태양의_은총 to 12,
                태양의_가호 to 4,
                장인의_재봉술1 to 1
        )
}

class Tier4Weapon(level: Level,  filterList: List<Materials> = emptyList()): AdvancedRefinementRecipe(level, filterList) {
        override val internalRequiredMaterialsTable = when(level) {
                L10 -> mapOf(
                        운명의_파괴석 to 600,
                        운명의_돌파석 to 16,
                        아비도스_융화_재료 to 25,
                        운명의_파편 to 5000,
                        골드 to 1125
                )
                else -> mapOf()
        }
        
        override val requiredAdditionalMaterialsTable = mapOf(
                용암의_숨결 to 12,
                장인의_야금술1 to 1
        )
}

class Tier4Armor(level: Level,  filterList: List<Materials> = emptyList()): AdvancedRefinementRecipe(level, filterList) {
        override val internalRequiredMaterialsTable = when(level) {
                L10 -> mapOf(
                        운명의_수호석 to 500,
                        운명의_돌파석 to 12,
                        아비도스_융화_재료 to 15,
                        운명의_파편 to 3000,
                        골드 to 950
                )
                else -> mapOf()
        }
        
        override val requiredAdditionalMaterialsTable = mapOf(
                빙하의_숨결 to 12,
                장인의_재봉술1 to 1
        )
}