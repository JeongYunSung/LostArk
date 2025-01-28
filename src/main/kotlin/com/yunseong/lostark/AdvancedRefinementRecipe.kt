package com.yunseong.lostark

import com.yunseong.lostark.Level.*
import com.yunseong.lostark.Materials.*

sealed class AdvancedRefinementRecipe(val level: Level, private val filterList: List<Materials> = emptyList()) {
        fun requiredMaterialsTable(): Map<Materials, Int> {
                return internalRequiredMaterialsTable.filterKeys { !filterList.contains(it) }
        }

        fun requiredAdditionalMaterialsTable(): Map<Materials, Int> {
                return internalRequiredAdditionalMaterialsTable.filterKeys { !filterList.contains(it) }
        }
        
        abstract val internalRequiredMaterialsTable: Map<Materials, Int>
        
        abstract val internalRequiredAdditionalMaterialsTable: Map<Materials, Int>
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
                L20 -> mapOf(
                        정제된_파괴강석 to 1600,
                        찬란한_명예의_돌파석 to 36,
                        최상급_오레하_융화제 to 33,
                        명예의_파편 to 17000,
                        골드 to 2500
                )
                else -> mapOf()
        }
        
        override val internalRequiredAdditionalMaterialsTable = when(level) {
                L10 -> mapOf(
                        태양의_축복 to 24,
                        태양의_은총 to 12,
                        태양의_가호 to 4,
                        장인의_야금술1 to 1
                )
                L20 -> mapOf(
                        태양의_축복 to 36,
                        태양의_은총 to 18,
                        태양의_가호 to 6,
                        장인의_야금술2 to 1
                )
                else -> mapOf()
        }
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
                L20 -> mapOf(
                        정제된_수호강석 to 1300,
                        찬란한_명예의_돌파석 to 28,
                        최상급_오레하_융화제 to 20,
                        명예의_파편 to 11000,
                        골드 to 1800
                )
                else -> mapOf()
        }

        override val internalRequiredAdditionalMaterialsTable = when(level) {
                L10 -> mapOf(
                        태양의_축복 to 24,
                        태양의_은총 to 12,
                        태양의_가호 to 4,
                        장인의_재봉술1 to 1
                )
                L20 -> mapOf(
                        태양의_축복 to 36,
                        태양의_은총 to 18,
                        태양의_가호 to 6,
                        장인의_재봉술2 to 1
                )
                else -> mapOf()
        }
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
                L20 -> mapOf(
                        운명의_파괴석 to 1100,
                        운명의_돌파석 to 22,
                        아비도스_융화_재료 to 27,
                        운명의_파편 to 10000,
                        골드 to 2500
                )
                L30 -> mapOf(
                        운명의_파괴석 to 1200,
                        운명의_돌파석 to 25,
                        아비도스_융화_재료 to 28,
                        운명의_파편 to 11500,
                        골드 to 3000
                )
                L40 -> mapOf(
                        운명의_파괴석 to 1400,
                        운명의_돌파석 to 32,
                        아비도스_융화_재료 to 30,
                        운명의_파편 to 13000,
                        골드 to 4000
                )
        }

        override val internalRequiredAdditionalMaterialsTable = when(level) {
                L10 -> mapOf(
                        용암의_숨결 to 12,
                        장인의_야금술1 to 1
                )
                L20 -> mapOf(
                        용암의_숨결 to 18,
                        장인의_야금술2 to 1
                )
                L30 -> mapOf(
                        용암의_숨결 to 24
                )
                L40 -> mapOf(
                        용암의_숨결 to 30
                )
        }
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
                L20 -> mapOf(
                        운명의_수호석 to 900,
                        운명의_돌파석 to 16,
                        아비도스_융화_재료 to 16,
                        운명의_파편 to 6000,
                        골드 to 1800
                )
                L30 -> mapOf(
                        운명의_수호석 to 1000,
                        운명의_돌파석 to 18,
                        아비도스_융화_재료 to 17,
                        운명의_파편 to 7000,
                        골드 to 2000
                )
                L40 -> mapOf(
                        운명의_수호석 to 1200,
                        운명의_돌파석 to 23,
                        아비도스_융화_재료 to 19,
                        운명의_파편 to 8000,
                        골드 to 2400
                )
        }

        override val internalRequiredAdditionalMaterialsTable = when(level) {
                L10 -> mapOf(
                        빙하의_숨결 to 12,
                        장인의_재봉술1 to 1
                )
                L20 -> mapOf(
                        빙하의_숨결 to 18,
                        장인의_재봉술2 to 1
                )
                L30 -> mapOf(
                        빙하의_숨결 to 24,
                )
                L40 -> mapOf(
                        빙하의_숨결 to 30
                )
        }
}
